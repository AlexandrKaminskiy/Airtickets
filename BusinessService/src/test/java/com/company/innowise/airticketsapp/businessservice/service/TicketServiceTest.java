package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.model.*;
import com.company.innowise.airticketsapp.businessservice.repository.TicketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @Test
    void getAll() {
    }

    @Test
    void testGetAll() {
    }

    @Test
    void getTicket() {
        Ticket ticket = new Ticket();
        ticket.setPrice(BigDecimal.valueOf(1000));
        ticket.setTimeArrive(LocalDate.now());
        ticket.setTimeDeparture(LocalDate.now());
        Passenger passenger = new Passenger();
        passenger.setBirthdate(LocalDate.now());
        passenger.setFirstname("alexandr");
        passenger.setLastname("kaminskiy");
        passenger.setRole(Role.ADMIN);
        passenger.setPassport("AB1234567");
        ticket.setPassenger(passenger);
        Airport airport = new Airport();
        airport.setCountry("Belarus");
        airport.setName("National Minsk Airport");
        airport.setTown("Minsk");
        ticket.setTo(airport);
        ticket.setFrom(airport);
        Company company = new Company();
        company.setName("Belavia");
        ticket.setCompany(company);
        ticketService.addTicket(ticket);
        Assertions.assertEquals(1, ticketService.getAll(new HashMap<>(Map.of())).size());
    }

    @Test
    void addTicket() {
    }

    @Test
    void deleteTicket() {
    }
}