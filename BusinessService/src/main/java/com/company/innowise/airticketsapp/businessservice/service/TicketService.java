package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.dto.TicketDto;
import com.company.innowise.airticketsapp.businessservice.exception.BusinessException;
import com.company.innowise.airticketsapp.businessservice.mapper.impl.TicketMapper;
import com.company.innowise.airticketsapp.businessservice.model.Flight;
import com.company.innowise.airticketsapp.businessservice.model.Ticket;
import com.company.innowise.airticketsapp.businessservice.model.Ticket_;
import com.company.innowise.airticketsapp.businessservice.repository.TicketRepository;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.FlightSpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.TicketSpecificationBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public List<TicketDto> getAll(Map<String, Object> parameters, int size, int page) {
        Specification<Ticket> specification = getSpecification(parameters);
        return ticketRepository.findAll(specification,
                        Pageable.ofSize(size).withPage(page)).stream()
                .map(ticketMapper::toDto)
                .toList();
    }

    public TicketDto getTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new BusinessException("ticket not found"));
        if (ticket.getPassenger() != null) {
            throw new BusinessException("information about this ticket cannot be shown");
        }
        return ticketMapper.toDto(ticket);
    }

    @Transactional
    public void addTickets(Flight flight, Integer seatsCount, BigDecimal price) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < seatsCount; i++) {
            Ticket ticket = new Ticket();
            ticket.setSeatNo(i);
            ticket.setPrice(price);
            ticket.setFlight(flight);
            tickets.add(ticket);
        }
        ticketRepository.saveAll(tickets);
    }

    private Specification<Ticket> getSpecification(Map<String, Object> parameters) {
        return (root, query, criteriaBuilder) -> {
            Specification<Ticket> ticketSpecification = ticketSpecificationBuilder
                    .getSpecification(Optional.empty(), parameters);
            Join<Ticket, Flight> ticketFlightJoin = root.join(Ticket_.FLIGHT);
            Specification<Ticket> flightSpecification = flightSpecificationBuilder
                    .getSpecification(Optional.of(ticketFlightJoin), parameters);
            Predicate ticketPassenger = root.get(Ticket_.PASSENGER).isNull();
            Specification<Ticket> specification = ticketSpecification
                    .and(flightSpecification);
            Predicate predicate = specification.toPredicate(root, query, criteriaBuilder);
            return criteriaBuilder.and(predicate, ticketPassenger);
        };
    }

}