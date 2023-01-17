package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.model.Airport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AirportServiceTest {

    private final AirportService airportService;

    @Autowired
    public AirportServiceTest(AirportService airportService) {
        this.airportService = airportService;
    }


    @Test
    void getAll() {

    }

    @Test
    void testGetAll() {
    }

    @Test
    void addAirport() {

    }

    @Test
    void deleteAirport() {
    }
}