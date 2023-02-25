package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.dto.NewPassengerDto;
import com.company.innowise.airticketsapp.businessservice.dto.PassengerCredentials;
import com.company.innowise.airticketsapp.businessservice.dto.PassengerDto;
import com.company.innowise.airticketsapp.businessservice.dto.Token;
import com.company.innowise.airticketsapp.businessservice.exception.BusinessException;
import com.company.innowise.airticketsapp.businessservice.mapper.impl.NewPassengerMapper;
import com.company.innowise.airticketsapp.businessservice.mapper.impl.PassengerMapper;
import com.company.innowise.airticketsapp.businessservice.model.JwtHolder;
import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.model.Role;
import com.company.innowise.airticketsapp.businessservice.repository.JwtRepository;
import com.company.innowise.airticketsapp.businessservice.repository.PassengerRepository;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.PassengerSpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.TicketSpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.security.JwtUtils;
import com.company.innowise.airticketsapp.businessservice.security.PassengerDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerSpecificationBuilder passengerSpecificationBuilder;
    private final PassengerMapper passengerMapper;
    private final NewPassengerMapper newPassengerMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final String ERROR_MESSAGE = "passenger not found";
    private final JwtRepository jwtRepository;

    public Passenger getByDetails() {
        UserDetails passengerDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return passengerRepository
                .getPassengerByUsername(passengerDetails.getUsername())
                .orElseThrow(() -> new BusinessException(ERROR_MESSAGE));

    }

    public List<PassengerDto> getAll(Map<String, Object> parameters, int size, int page) {
        Specification<Passenger> specification = getSpecification(parameters);
        return passengerRepository.findAll(specification, Pageable.ofSize(size).withPage(page)).stream()
                .map(passengerMapper::toDto)
                .toList();
    }

    public PassengerDto getPassenger(Long id) {
        return passengerMapper.toDto(passengerRepository.findById(id).orElseThrow(()->new BusinessException(ERROR_MESSAGE)));
    }

    public PassengerDto getProfile() {
        PassengerDetails passengerDetails = (PassengerDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return passengerMapper
                .toDto(passengerRepository
                        .getPassengerByUsername(passengerDetails.getUsername())
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
        passenger.setRoles(new HashSet<>(Set.of(Role.ROLE_PASSENGER)));
        passengerRepository.save(passenger);
        log.info("PASSENGER {} WAS REGISTERED", passenger.getUsername());
        return passenger.getUsername();
    }

    @Transactional
    public Token signIn(PassengerCredentials passengerCredentials) {
        String password = passengerCredentials.getPassword();
        String username = passengerCredentials.getUsername();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String accessToken = jwtUtils.createToken((UserDetails) authentication.getPrincipal(), true);
        String refreshToken = jwtUtils.createToken((UserDetails) authentication.getPrincipal(), false);
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

    private Specification<Passenger> getSpecification(Map<String, Object> parameters) {
        return (root, query, criteriaBuilder) -> {

            Specification<Passenger> airportSpecification = passengerSpecificationBuilder.getSpecification(Optional.empty(), parameters);
            return airportSpecification.and(airportSpecification)
                    .toPredicate(root, query, criteriaBuilder);
        };
    }

    @Transactional
    public void changeRole(String username, Set<Role> roles) {
        Passenger passenger = passengerRepository
                .getPassengerByUsername(username).orElseThrow(() -> new BusinessException(ERROR_MESSAGE));
        passenger.setRoles(roles);
        passengerRepository.save(passenger);
        log.info("PASSENGER {} ROLES WERE UPDATED TO", roles);
    }
}
