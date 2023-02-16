package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.dto.AirportDto;
import com.company.innowise.airticketsapp.businessservice.model.Airport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Map;

@SpringBootTest
class AirportServiceTest {

    private final AirportService airportService;

    @Autowired
    AirportServiceTest(AirportService airportService) {
        this.airportService = airportService;
    }

    @Test
    void addAirport() {
        AirportDto airportDto = new AirportDto();
        airportDto.setName("minskiy");
        airportDto.setTown("minsk");
        airportDto.setCountry("belarus");
        airportService.addAirport(airportDto);
        Map<String, Object> params = Map.of("town", "minsk", "country", "belarus");
        List<Airport> all = airportService.getAll(params, 10, 0);
        Assertions.assertTrue(all.size() >= 1);
    }

}