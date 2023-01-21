package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.model.*;
import com.company.innowise.airticketsapp.businessservice.repository.TicketRepository;
import com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.builderimpl.AirportSpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.builderimpl.CompanySpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.builderimpl.PassengerSpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.builderimpl.TicketSpecificationBuilder;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    private final TicketSpecificationBuilder ticketSpecificationBuilder;

    private final PassengerSpecificationBuilder passengerSpecificationBuilder;

    private final AirportSpecificationBuilder airportSpecificationBuilder;

    private final CompanySpecificationBuilder companySpecificationBuilder;

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getAll(Map<String, Object> parameters) {
        Specification<Ticket> specification = getSpecification(parameters);
        return ticketRepository.findAll(specification);
    }

    public Ticket getTicket(Long id) {
        return ticketRepository.getReferenceById(id);
    }

    public void addTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public void deleteTicket(long id) {
        ticketRepository.deleteById(id);
    }

    private Specification<Ticket> getSpecification(Map<String, Object> parameters) {
        return (root, query, criteriaBuilder) -> {
            Specification<Ticket> ticketSpecification = ticketSpecificationBuilder.getSpecification(Optional.empty(), parameters);
            Object to = parameters.get("to");
            Object from = parameters.get("from");

            Join<Ticket, Airport> ticketAirportJoinTo = root.join("to");
            parameters.put("town", to);
            Specification<Ticket> airportSpecificationTo = airportSpecificationBuilder.getSpecification(Optional.of(ticketAirportJoinTo), parameters);

            Join<Ticket, Airport> ticketAirportJoinFrom = root.join("from");
            parameters.put("town", from);
            Specification<Ticket> airportSpecificationFrom = airportSpecificationBuilder.getSpecification(Optional.of(ticketAirportJoinFrom), parameters);

            Join<Ticket, Passenger> ticketPassengerJoin = root.join(Ticket_.PASSENGER);
            Specification<Ticket> passengerSpecification = passengerSpecificationBuilder.getSpecification(Optional.of(ticketPassengerJoin), parameters);
            Join<Ticket, Company> ticketCompanyJoin = root.join(Ticket_.COMPANY);
            Specification<Ticket> companySpecification = companySpecificationBuilder.getSpecification(Optional.of(ticketCompanyJoin), parameters);

            Specification<Ticket> specification = ticketSpecification
                    .and(airportSpecificationFrom)
                    .and(passengerSpecification)
                    .and(companySpecification)
                    .and(airportSpecificationTo);
            return specification.toPredicate(root, query, criteriaBuilder);
        };
    }

}
