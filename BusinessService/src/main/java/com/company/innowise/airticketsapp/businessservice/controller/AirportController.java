package com.company.innowise.airticketsapp.businessservice.controller;

import com.company.innowise.airticketsapp.businessservice.dto.AirportDto;
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

    @GetMapping("/")
    public List<Airport> getAll(@RequestParam(defaultValue = "10", required = false) Integer size,
                                @RequestParam(defaultValue = "0", required = false) Integer page,
                                @RequestParam(required = false) String town,
                                @RequestParam(required = false) String country,
                                @RequestParam(required = false) String name) {
        Map<String, Object> params = new HashMap<>();
        params.compute(TOWN, (k, v)-> town);
        params.compute(COUNTRY, (k, v)-> country);
        params.compute(NAME, (k, v)-> name);
        return airportService.getAll(params, size, page);
    }

    @GetMapping("/{id}")
    public Airport getOne(@PathVariable Integer id) {
        return airportService.getAirport(id);
    }

    @PostMapping("/")
    public AirportDto addAirport(@Valid @RequestBody AirportDto airportDto) {
        return airportService.addAirport(airportDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable Integer id) {
        airportService.deleteAirport(id);
    }

}
