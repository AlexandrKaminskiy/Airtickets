package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.model.Flight;
import com.company.innowise.airticketsapp.businessservice.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootTest
class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @MockBean
    private TicketRepository ticketRepository;

    @Test
    void addTickets() {

        ticketService.addTickets(new Flight(), 0, BigDecimal.valueOf(0));
        Mockito.verify(ticketRepository, Mockito.times(1))
                .saveAll(ArgumentMatchers.any(ArrayList.class));

    }

}