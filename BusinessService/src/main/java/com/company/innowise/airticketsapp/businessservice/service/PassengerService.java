package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.dto.PassengerDto;
import com.company.innowise.airticketsapp.businessservice.exception.BusinessException;
import com.company.innowise.airticketsapp.businessservice.mapper.impl.PassengerMapper;
import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.model.Ticket;
import com.company.innowise.airticketsapp.businessservice.repository.PassengerRepository;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.PassengerSpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.TicketSpecificationBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerSpecificationBuilder passengerSpecificationBuilder;
    private final TicketSpecificationBuilder ticketSpecificationBuilder;
    private final PassengerMapper passengerMapper;
    private final PasswordEncoder passwordEncoder;

    public List<PassengerDto> getAll(int size, int page) {
        return passengerRepository.findAll(Pageable.ofSize(size).withPage(page)).stream()
                .map(passengerMapper::toDto)
                .toList();
    }

    public List<PassengerDto> getAll(Map<String, Object> parameters, int size, int page) {
        Specification<Passenger> specification = getSpecification(parameters);
        return passengerRepository.findAll(specification, Pageable.ofSize(size).withPage(page)).stream()
                .map(passengerMapper::toDto)
                .toList();
    }

    public PassengerDto getPassenger(long id) {
        return passengerMapper.toDto(passengerRepository.findById(id).orElseThrow(()->new BusinessException("passenger not found")));
    }

    @Transactional
    public PassengerDto signUp(PassengerDto passengerDto) {
        Passenger passenger = passengerMapper.toModel(passengerDto);
        List<Passenger> passengers =
                passengerRepository.getPassengerByUsernameOrEmail(passenger.getUsername(), passengerDto.getEmail());
        if (!passengers.isEmpty()) {
            throw new BusinessException("You can't create passenger with this email and username");
        }
        passenger.setPassword(passwordEncoder.encode(passenger.getPassword()));
        passengerRepository.save(passenger);
        return passengerDto;
    }

    @Transactional
    public void deletePassenger(long id) {
        Passenger passenger = passengerRepository.findById(id).orElseThrow(() -> new BusinessException("passenger not found"));
        passengerRepository.delete(passenger);
    }

    private Specification<Passenger> getSpecification(Map<String, Object> parameters) {
        return (root, query, criteriaBuilder) -> {

            Specification<Passenger> airportSpecification = passengerSpecificationBuilder.getSpecification(Optional.empty(), parameters);
            Join<Passenger, Ticket> passengerTicketJoin = root.join("ticket");
            Specification<Passenger> ticketSpecification = ticketSpecificationBuilder.getSpecification(Optional.of(passengerTicketJoin), parameters);
            return airportSpecification.and(airportSpecification)
                    .and(ticketSpecification)
                    .toPredicate(root, query, criteriaBuilder);
        };
    }

}
