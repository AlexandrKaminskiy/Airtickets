package com.company.innowise.airticketsapp.businessservice.controller;

import com.company.innowise.airticketsapp.businessservice.dto.AirportDto;
import com.company.innowise.airticketsapp.businessservice.findingdto.AirportFindingDto;
import com.company.innowise.airticketsapp.businessservice.model.Airport;
import com.company.innowise.airticketsapp.businessservice.service.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.company.innowise.airticketsapp.businessservice.model.Airport_.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airport")
public class AirportController {

    private final AirportService airportService;

    @PostMapping("/all")
    public List<Airport> getAll(@RequestBody @Valid AirportFindingDto airportFindingDto) {

        Map<String, Object> params = new HashMap<>();
        params.compute(TOWN, (k, v) -> airportFindingDto.getTown());
        params.compute(COUNTRY, (k, v) -> airportFindingDto.getCountry());
        params.compute(NAME, (k, v) -> airportFindingDto.getName());

        return airportService.getAll(params, airportFindingDto.getPageRequest());
    }

    @GetMapping("/{id}")
    public Airport getOne(@PathVariable Integer id) {
        return airportService.getById(id);
    }

    @PostMapping
    public AirportDto addAirport(@Valid @RequestBody AirportDto airportDto) {
        return airportService.addAirport(airportDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable Integer id) {
        airportService.deleteAirport(id);
    }

}
