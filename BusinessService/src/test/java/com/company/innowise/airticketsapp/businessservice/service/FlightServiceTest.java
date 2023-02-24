package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.dto.NewFlightDto;
import com.company.innowise.airticketsapp.businessservice.dto.UpdatedFlightDto;
import com.company.innowise.airticketsapp.businessservice.model.Airport;
import com.company.innowise.airticketsapp.businessservice.model.Flight;
import com.company.innowise.airticketsapp.businessservice.model.Ticket;
import com.company.innowise.airticketsapp.businessservice.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FlightServiceTest {

    @Autowired
    private FlightService flightService;

    @MockBean
    private FlightRepository flightRepository;

    @MockBean
    private AirportService airportService;

    @MockBean
    private TicketService ticketService;

    @Test
    void addFlight() {
        Mockito.doReturn(new Airport())
                        .when(airportService)
                        .getById(ArgumentMatchers.anyLong());
        Mockito.doNothing()
                .when(ticketService)
                .addTickets(
                        ArgumentMatchers.any(Flight.class),
                        ArgumentMatchers.anyInt(),
                        ArgumentMatchers.any(BigDecimal.class)
                );
        NewFlightDto newFlightDto = new NewFlightDto();
        newFlightDto.setFromId(0);
        newFlightDto.setToId(0);
        newFlightDto.setTimeDeparture(LocalDateTime.now());
        newFlightDto.setTimeArrive(LocalDateTime.now());
        newFlightDto.setPrice(0d);
        flightService.addFlight(newFlightDto);
        Mockito.verify(flightRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Flight.class));
    }

    @Test
    void deleteFlight() {
        Mockito.doReturn(Optional.of(new Flight()))
                .when(flightRepository)
                .findById(ArgumentMatchers.anyLong());
        flightService.deleteFlight(0);
        Mockito.verify(flightRepository, Mockito.times(1))
                .delete(ArgumentMatchers.any(Flight.class));
    }

    @Test
    void updateFlight() {
        Mockito.doReturn(Optional.of(new Flight()))
                .when(flightRepository)
                .findById(ArgumentMatchers.anyLong());
        flightService.updateFlight(0L, new UpdatedFlightDto());
        Mockito.verify(flightRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Flight.class));
    }
}