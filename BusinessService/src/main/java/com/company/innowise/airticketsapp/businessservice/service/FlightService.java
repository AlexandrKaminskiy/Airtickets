package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.model.*;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.AirportSpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.FlightSpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.TicketSpecificationBuilder;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static com.company.innowise.airticketsapp.businessservice.model.Flight_.*;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightSpecificationBuilder flightSpecificationBuilder;
    private final TicketSpecificationBuilder ticketSpecificationBuilder;
    private final AirportSpecificationBuilder airportSpecificationBuilder;

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
