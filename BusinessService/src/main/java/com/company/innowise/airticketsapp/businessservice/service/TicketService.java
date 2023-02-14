package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.dto.TicketDto;
import com.company.innowise.airticketsapp.businessservice.mapper.impl.TicketMapper;
import com.company.innowise.airticketsapp.businessservice.model.Flight;
import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.model.Ticket;
import com.company.innowise.airticketsapp.businessservice.model.Ticket_;
import com.company.innowise.airticketsapp.businessservice.repository.TicketRepository;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.FlightSpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.TicketSpecificationBuilder;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketSpecificationBuilder ticketSpecificationBuilder;
    private final FlightSpecificationBuilder flightSpecificationBuilder;
    private final TicketMapper ticketMapper;

    public void purchaseTicket(Passenger passenger, Integer ticketId) {
        Ticket ticket = ticketRepository.getReferenceById(Long.valueOf(ticketId));
        ticket.setPassenger(passenger);
        ticketRepository.save(ticket);
    }

    public List<TicketDto> getAll(Map<String, Object> parameters, int size, int page) {
        Specification<Ticket> specification = getSpecification(parameters);
        return ticketRepository.findAll(specification,
                        Pageable.ofSize(size).withPage(page)).stream()
                .map(ticketMapper::toDto)
                .toList();
    }

    public List<TicketDto> getPassengerTickets(int size, int page) {
        Specification<Ticket> specification = passengerTicketsSpecification();
        return ticketRepository.findAll(specification,
                        Pageable.ofSize(size).withPage(page)).stream()
                .map(ticketMapper::toDto)
                .toList();
    }

    public TicketDto getTicket(long id) {
        return ticketMapper.toDto(ticketRepository.getReferenceById(id));
    }

    public void addTickets(Flight flight, BigDecimal price) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < flight.getSeatsCount(); i++) {
            Ticket ticket = new Ticket();
            ticket.setSeatNo(i);
            ticket.setPrice(price);
            ticket.setFlight(flight);
            tickets.add(ticket);
        }
        ticketRepository.saveAll(tickets);
    }
    public void deleteTicket(long id) {
        ticketRepository.deleteById(id);
    }

    private Specification<Ticket> getSpecification(Map<String, Object> parameters) {
        return (root, query, criteriaBuilder) -> {
            Specification<Ticket> ticketSpecification = ticketSpecificationBuilder.getSpecification(Optional.empty(), parameters);
            Join<Ticket, Flight> ticketFlightJoin = root.join(Ticket_.FLIGHT);
            Specification<Ticket> flightSpecification = flightSpecificationBuilder.getSpecification(Optional.of(ticketFlightJoin), parameters);
            Specification<Ticket> specification = ticketSpecification
                    .and(flightSpecification);
            return specification.toPredicate(root, query, criteriaBuilder);
        };
    }

    private Specification<Ticket> passengerTicketsSpecification() {
        return (root, query, criteriaBuilder) -> {
            Join<Ticket, Passenger> ticketPassengerJoin = root.join(Ticket_.PASSENGER);
            return ticketPassengerJoin.getOn();
        };
    }

}
