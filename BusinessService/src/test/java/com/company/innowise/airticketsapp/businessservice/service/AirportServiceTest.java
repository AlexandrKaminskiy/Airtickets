package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.model.Airport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class AirportServiceTest {

    private final AirportService airportService;

    @Autowired
    public AirportServiceTest(AirportService airportService) {
        this.airportService = airportService;
    }
    @Test
    void addAndDeleteAirport() {
        Airport airport = airportService.addAirport(new Airport("jjj","bbb","ccc"));
        long delId = airport.getId();
        Assertions.assertEquals("jjj", airport.getName());
        airportService.deleteAirport(delId);
    }

    @Test
    public void createAndGet() {
        Airport airport = airportService.addAirport(new Airport("ooo","bbb","ccc"));
        long id = airport.getId();
        Airport airport1 = airportService.getAirport(id);
        Assertions.assertEquals(id, airport1.getId());
    }

    @Test
    void getAll() {
        Assertions.assertEquals(4, airportService.getAll().size());
    }

    @Test
    void testGetAll() {
        Assertions.assertEquals(2, airportService.getAll(Map.of("name","jjj")).size());
    }


}