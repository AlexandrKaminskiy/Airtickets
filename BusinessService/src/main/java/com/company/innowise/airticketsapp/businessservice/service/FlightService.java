package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.dto.FlightDto;
import com.company.innowise.airticketsapp.businessservice.mapper.impl.FlightMapper;
import com.company.innowise.airticketsapp.businessservice.model.Airport;
import com.company.innowise.airticketsapp.businessservice.model.Flight;
import com.company.innowise.airticketsapp.businessservice.model.Ticket;
import com.company.innowise.airticketsapp.businessservice.repository.FlightRepository;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.AirportSpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.FlightSpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.TicketSpecificationBuilder;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.company.innowise.airticketsapp.businessservice.model.Flight_.*;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightSpecificationBuilder flightSpecificationBuilder;
    private final TicketSpecificationBuilder ticketSpecificationBuilder;
    private final AirportSpecificationBuilder airportSpecificationBuilder;
    private final FlightMapper flightMapper;
    private final FlightRepository flightRepository;

    public List<FlightDto> getAll(int size, int page) {
        return flightRepository.findAll(Pageable.ofSize(size).withPage(page)).stream()
                .map(flightMapper::toDto)
                .toList();
    }

    public List<FlightDto> getAll(Map<String, Object> parameters, int size, int page) {
        Specification<Flight> specification = getSpecification(parameters);
        return flightRepository.findAll(specification, Pageable.ofSize(size).withPage(page)).stream()
                .map(flightMapper::toDto)
                .toList();
    }

    public FlightDto getFlight(long id) {
        return flightMapper.toDto(flightRepository.getReferenceById(id));
    }

    public FlightDto addFlight(FlightDto flightDto) {
        Flight flight = flightMapper.toModel(flightDto);
        flightRepository.save(flight);
        return flightDto;
    }

    public void deleteFlight(long id) {
        flightRepository.deleteById(id);
    }

    private Specification<Flight> getSpecification(Map<String, Object> parameters) {
        return (root, query, criteriaBuilder) -> {
            Specification<Flight> flightSpecification =
                    flightSpecificationBuilder.getSpecification(Optional.empty(), parameters);
            Object to = parameters.get(TO);
            Object from = parameters.get(FROM);
            String town = "town";
            Join<Flight, Airport> flightAirportJoinTo = root.join(TO);
            parameters.put(town, to);
            Specification<Flight> airportSpecificationTo =
                    airportSpecificationBuilder.getSpecification(Optional.of(flightAirportJoinTo), parameters);
            Join<Flight, Airport> flightAirportJoinFrom = root.join(FROM);
            parameters.put(town, from);
            Specification<Flight> airportSpecificationFrom =
                    airportSpecificationBuilder.getSpecification(Optional.of(flightAirportJoinFrom), parameters);
            Join<Flight, Ticket> ticketFlightJoin = root.join(TICKETS);
            Specification<Flight> ticketSpecification =
                    ticketSpecificationBuilder.getSpecification(Optional.of(ticketFlightJoin), parameters);
            Specification<Flight> specification = flightSpecification
                    .and(ticketSpecification)
                    .and(airportSpecificationTo)
                    .and(airportSpecificationFrom);
            return specification.toPredicate(root, query, criteriaBuilder);
        };
    }
}
