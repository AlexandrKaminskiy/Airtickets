package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.dto.TicketDto;
import com.company.innowise.airticketsapp.businessservice.mapper.impl.TicketMapper;
import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.model.Ticket;
import com.company.innowise.airticketsapp.businessservice.model.Ticket_;
import com.company.innowise.airticketsapp.businessservice.repository.TicketRepository;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.AirportSpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.PassengerSpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.TicketSpecificationBuilder;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    private final TicketMapper ticketMapper;

    public List<TicketDto> getAll(int size, int page) {
        return ticketRepository.findAll(Pageable.ofSize(size).withPage(page)).stream()
                .map(ticketMapper::toDto)
                .toList();
    }

    public List<TicketDto> getAll(Map<String, Object> parameters, int size, int page) {
        Specification<Ticket> specification = getSpecification(parameters);
        return ticketRepository.findAll(specification, Pageable.ofSize(size).withPage(page)).stream()
                .map(ticketMapper::toDto)
                .toList();
    }

    public TicketDto getTicket(long id) {
        return ticketMapper.toDto(ticketRepository.getReferenceById(id));
    }

    public TicketDto addTicket(TicketDto ticketDto) {
        Ticket ticket = ticketMapper.toModel(ticketDto);
        ticketRepository.save(ticket);
        return ticketDto;
    }
    public void deleteTicket(long id) {
        ticketRepository.deleteById(id);
    }

    private Specification<Ticket> getSpecification(Map<String, Object> parameters) {
        return (root, query, criteriaBuilder) -> {
            Specification<Ticket> ticketSpecification = ticketSpecificationBuilder.getSpecification(Optional.empty(), parameters);
            Join<Ticket, Passenger> ticketPassengerJoin = root.join(Ticket_.PASSENGER);
            Specification<Ticket> passengerSpecification = passengerSpecificationBuilder.getSpecification(Optional.of(ticketPassengerJoin), parameters);
//            Join<Ticket, Flight> ticketFlightJoin = root.join(Ticket_.FLIGHT);
//            Specification<Ticket> flightSpecification = passengerSpecificationBuilder.getSpecification(Optional.of(ticketFlightJoin), parameters);
            Specification<Ticket> specification = ticketSpecification
                    .and(passengerSpecification);
            return specification.toPredicate(root, query, criteriaBuilder);
        };
    }

}
