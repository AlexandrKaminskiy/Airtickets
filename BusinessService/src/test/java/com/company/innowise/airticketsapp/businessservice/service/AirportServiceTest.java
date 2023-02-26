package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.dto.AirportDto;
import com.company.innowise.airticketsapp.businessservice.model.Airport;
import com.company.innowise.airticketsapp.businessservice.repository.AirportRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;

@SpringBootTest
class AirportServiceTest {

    @Autowired
    private AirportService airportService;

    @MockBean
    private AirportRepository airportRepository;

    @Test
    void addAirport() {
        airportService.addAirport(new AirportDto());
        Mockito.verify(airportRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Airport.class));
    }

    @Test
    void deleteAirport() {
        Mockito.doReturn(Optional.of(new Airport()))
                        .when(airportRepository)
                        .findById(ArgumentMatchers.anyLong());
        airportService.deleteAirport(ArgumentMatchers.anyLong());
        Mockito.verify(airportRepository, Mockito.times(1))
                .delete(ArgumentMatchers.any(Airport.class));
    }

}