package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.model.Ticket;
import com.company.innowise.airticketsapp.businessservice.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;

@SpringBootTest
class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @MockBean
    private PassengerService passengerService;

    @MockBean
    private TicketRepository ticketRepository;

    @Test
    void purchaseTicket() {
        Mockito.doReturn(new Passenger())
                .when(passengerService)
                .getByDetails();
        Mockito.doReturn(Optional.of(new Ticket()))
                .when(ticketRepository)
                .findById(ArgumentMatchers.anyLong());
        paymentService.purchaseTicket(0L);
        Mockito.verify(ticketRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Ticket.class));
    }

    @Test
    void sellTicket() {
        Passenger passenger = new Passenger();
        Mockito.doReturn(passenger)
                .when(passengerService)
                .getByDetails();
        Ticket ticket = new Ticket();
        ticket.setPassenger(passenger);
        Mockito.doReturn(Optional.of(ticket))
                .when(ticketRepository)
                .findById(ArgumentMatchers.anyLong());
        paymentService.sellTicket(0L);
        Mockito.verify(ticketRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Ticket.class));
    }

}