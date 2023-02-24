package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.exception.BusinessException;
import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.model.Ticket;
import com.company.innowise.airticketsapp.businessservice.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final TicketRepository ticketRepository;
    private final PassengerService passengerService;

    @Transactional
    public void purchaseTicket(Long ticketId) {
        log.info("ATTEMPT TO BY TICKET WITH ID {}", ticketId);
        Passenger passenger = passengerService.getByDetails();
        Ticket ticket = ticketRepository
                .findById(ticketId).orElseThrow(() -> new BusinessException("ticket not found"));
        if (ticket.getPassenger() != null) {
            log.error("FAILED TO BYE TICKET WITH ID {}", ticketId);
            throw new BusinessException("You can't buy this ticket");
        }
        ticket.setPassenger(passenger);
        ticketRepository.save(ticket);
        log.info("TICKET WITH ID {} WAS BOUGHT BY {}", ticketId, passenger.getUsername());
    }

    @Transactional
    public void sellTicket(Long ticketId) {
        log.info("ATTEMPT TO SELL TICKET WITH ID {}", ticketId);
        Passenger passenger = passengerService.getByDetails();
        Ticket ticket = ticketRepository
                .findById(ticketId).orElseThrow(() -> new BusinessException("ticket not found"));
        if (ticket.getPassenger() != passenger) {
            log.error("FAILED TO SELL TICKET WITH ID {}", ticketId);
            throw new BusinessException("You can't sell this ticket");
        }
        ticket.setPassenger(null);
        ticketRepository.save(ticket);
        log.info("TICKET WITH ID {} WAS SOLD BY {}", ticketId, passenger.getUsername());
    }

}
