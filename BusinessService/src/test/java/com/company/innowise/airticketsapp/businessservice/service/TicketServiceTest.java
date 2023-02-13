package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.model.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TicketServiceTest {

    private final TicketService ticketService;

    public TicketServiceTest(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Test
    void getAll() {

    }

    @Test
    void testGetAll() {
    }

    @Test
    void getTicket() {
    }

    @Test
    void addTicket() {
//        ticketService.addTicket(new Ticket());
    }

    @Test
    void deleteTicket() {
    }
}