package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.dto.*;
import com.company.innowise.airticketsapp.businessservice.exception.BusinessException;
import com.company.innowise.airticketsapp.businessservice.mail.MailService;
import com.company.innowise.airticketsapp.businessservice.mapper.impl.NewPassengerMapper;
import com.company.innowise.airticketsapp.businessservice.mapper.impl.PassengerMapper;
import com.company.innowise.airticketsapp.businessservice.model.ActivatorLink;
import com.company.innowise.airticketsapp.businessservice.model.JwtHolder;
import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.model.Role;
import com.company.innowise.airticketsapp.businessservice.repository.ActivatorLinkRepository;
import com.company.innowise.airticketsapp.businessservice.repository.JwtRepository;
import com.company.innowise.airticketsapp.businessservice.repository.PassengerRepository;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.specificationimpl.PassengerSpecification;
import com.company.innowise.airticketsapp.businessservice.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerSpecification passengerSpecification;
    private final PassengerMapper passengerMapper;
    private final NewPassengerMapper newPassengerMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final JwtRepository jwtRepository;
    private final ActivatorLinkRepository activatorLinkRepository;
    private final MailService mailService;
    private final RabbitTemplate rabbitTemplate;
    private final String ERROR_MESSAGE = "passenger not found";

    @Value("${rabbit.routing-key}")
    private String routingKey;

    @Value("${rabbit.exchange}")
    private String exchange;

    public Passenger getByUsername(String username) {
        return passengerRepository.getPassengerByUsername(username).orElseThrow(() -> new BusinessException(ERROR_MESSAGE));
    }

    public List<PassengerDto> getAll(Map<String, Object> parameters, int size, int page) {
        Specification<Passenger> specification = getSpecification(parameters);
        return passengerRepository.findAll(specification, Pageable.ofSize(size).withPage(page)).stream()
                .map(passengerMapper::toDto)
                .toList();
    }

    public PassengerDto getPassenger(String username) {
        return passengerMapper.toDto(passengerRepository.getPassengerByUsername(username)
                .orElseThrow(()->new BusinessException(ERROR_MESSAGE)));
    }

    public PassengerDto getProfile(Principal principal) {
        return passengerMapper
                .toDto(passengerRepository
                        .getPassengerByUsername(principal.getName())
                        .orElseThrow(()->new BusinessException(ERROR_MESSAGE)));
    }

    @Transactional
    public String signUp(NewPassengerDto passengerDto) {
        log.info("ATTEMPT TO REGISTER PASSENGER");
        Passenger passenger = newPassengerMapper.toModel(passengerDto);
        List<Passenger> passengers =
                passengerRepository.getPassengerByUsernameOrEmail(passenger.getUsername(), passengerDto.getEmail());
        if (!passengers.isEmpty()) {
            log.error("REGISTER WAS FAILED");
            throw new BusinessException("You can't create passenger with this email and username");
        }
        passenger.setPassword(passwordEncoder.encode(passenger.getPassword()));
        passenger.setIsActive(false);
        passengerRepository.save(passenger);
        String uuid = UUID.randomUUID().toString();
        ActivatorLink activatorLink = new ActivatorLink(uuid, passenger);
        activatorLinkRepository.save(activatorLink);
        mailService.sendMail(passenger.getEmail(), uuid);
        log.info("PASSENGER {} WAS REGISTERED BUT NOT ACTIVATED", passenger.getUsername());
        return passenger.getUsername();
    }

    @Transactional
    public Token signIn(PassengerCredentials passengerCredentials) {
        String password = passengerCredentials.getPassword();
        String username = passengerCredentials.getUsername();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String accessToken = jwtUtils.createToken(authentication, true);
        String refreshToken = jwtUtils.createToken(authentication, false);
        Passenger passenger = passengerRepository
                        .getPassengerByUsername(username).orElseThrow(() -> new BusinessException(ERROR_MESSAGE));
        jwtRepository.findByPassengerUsername(passenger.getUsername()).ifPresent(jwtRepository::delete);
        JwtHolder jwtHolder = new JwtHolder();
        jwtHolder.setAccessToken(accessToken);
        jwtHolder.setRefreshToken(refreshToken);
        jwtHolder.setPassenger(passenger);
        jwtRepository.save(jwtHolder);
        log.info("PASSENGER {} SIGNED IN", passengerCredentials.getUsername());
        return new Token(accessToken, refreshToken);
    }

    @Transactional
    public String activatePassenger(String uuid) {
        ActivatorLink link =
                activatorLinkRepository.findByUuid(uuid).orElseThrow(() -> new BusinessException("incorrect link"));
        Passenger passenger = link.getPassenger();
        passenger.setIsActive(true);
        passenger.setRoles(new HashSet<>(Set.of(Role.ROLE_PASSENGER)));
        passengerRepository.save(passenger);
        activatorLinkRepository.delete(link);
        CompletableFuture.runAsync(() -> rabbitTemplate.convertAndSend(exchange, routingKey,
                new UserInfo(passenger.getUsername(), LocalDateTime.now(), Activity.REGISTERED)));
        log.info("PASSENGER {} IS ACTIVE", passenger.getUsername());
        return "passenger is active";
    }

    @Transactional
    public Set<Role> changeRole(String username, Set<Role> roles) {
        Passenger passenger = passengerRepository
                .getPassengerByUsername(username).orElseThrow(() -> new BusinessException(ERROR_MESSAGE));
        passenger.setRoles(roles);
        passengerRepository.save(passenger);
        CompletableFuture.runAsync(() -> rabbitTemplate.convertAndSend(exchange, routingKey,
                new UserInfo(passenger.getUsername(), LocalDateTime.now(), Activity.CHANGE_ROLE)));
        log.info("PASSENGER {} ROLES WERE UPDATED TO {}", passenger.getId(), roles);
        return roles;
    }

    @Transactional
    public Token updateToken(Principal principal) {
        JwtHolder jwtHolder = jwtRepository.findByPassengerUsername(principal.getName())
                .orElseThrow(() -> new BusinessException(ERROR_MESSAGE));
        String newAccessToken = jwtUtils.createToken(principal, true);
        String newRefreshToken = jwtUtils.createToken(principal, false);
        jwtHolder.setAccessToken(newAccessToken);
        jwtHolder.setRefreshToken(newRefreshToken);
        jwtRepository.save(jwtHolder);
        return new Token(newAccessToken, newRefreshToken);
    }

    private Specification<Passenger> getSpecification(Map<String, Object> parameters) {
        return (root, query, criteriaBuilder) -> {
            Specification<Passenger> specification = passengerSpecification
                    .getSpecification(Optional.empty(), parameters);
            return specification.and(specification)
                    .toPredicate(root, query, criteriaBuilder);
        };
    }


}
