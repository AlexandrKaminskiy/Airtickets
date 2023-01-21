package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.model.*;
import com.company.innowise.airticketsapp.businessservice.repository.TicketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        ticket.setTimeArrive(LocalDateTime.now());
        ticket.setTimeDeparture(LocalDateTime.now());
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
        company.setCompanyName("Belavia");
        ticket.setCompany(company);
        ticketService.addTicket(ticket);
        Assertions.assertEquals(2, ticketService.getAll(new HashMap<>(Map.of("companyName", "Belavia",
                "firstname","alexandr",
                "to", "Minsk",
                "timeArrive", LocalDateTime.of(2020, 1, 1, 1,1,1))))
                .size());
    }

    @Test
    void addTicket() {
    }

    @Test
    void deleteTicket() {
    }
}