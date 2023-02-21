package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.exception.BusinessException;
import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.model.Ticket;
import com.company.innowise.airticketsapp.businessservice.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final TicketRepository ticketRepository;
    private final PassengerService passengerService;

    @Transactional
    public void purchaseTicket(Long ticketId) {
        Passenger passenger = passengerService.getByDetails();
        Ticket ticket = ticketRepository
                .findById(ticketId).orElseThrow(() -> new BusinessException("ticket not found"));
        if (ticket.getPassenger() != null) {
            throw new BusinessException("You can't buy this ticket");
        }
        ticket.setPassenger(passenger);
        ticketRepository.save(ticket);
    }

    @Transactional
    public void sellTicket(Long ticketId) {
        Passenger passenger = passengerService.getByDetails();
        Ticket ticket = ticketRepository
                .findById(ticketId).orElseThrow(() -> new BusinessException("ticket not found"));
        if (ticket.getPassenger() != passenger) {
            throw new BusinessException("You can't sell this ticket");
        }
        ticket.setPassenger(null);
        ticketRepository.save(ticket);
    }

}
