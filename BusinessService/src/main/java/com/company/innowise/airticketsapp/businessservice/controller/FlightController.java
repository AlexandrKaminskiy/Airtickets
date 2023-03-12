package com.company.innowise.airticketsapp.businessservice.controller;

import com.company.innowise.airticketsapp.businessservice.dto.FlightDto;
import com.company.innowise.airticketsapp.businessservice.dto.NewFlightDto;
import com.company.innowise.airticketsapp.businessservice.dto.UpdatedFlightDto;
import com.company.innowise.airticketsapp.businessservice.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public List<FlightDto> getAll(@PageableDefault Pageable pageable,
                                  @RequestParam String to,
                                  @RequestParam String from) {
        return flightService.getAll(to, from, pageable);
    }

    @GetMapping("/{id}")
    public FlightDto getOne(@PathVariable Integer id) {
        return flightService.getFlight(id);
    }

    @PostMapping()
    public FlightDto addFlight(@RequestBody @Valid NewFlightDto newFlightDto) {
        return flightService.addFlight(newFlightDto);
    }

    @PutMapping("/{flightId}")
    public FlightDto updateFlight(@PathVariable Long flightId,
                                  @RequestBody @Valid UpdatedFlightDto flightDto) {
        return flightService.updateFlight(flightId, flightDto);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Integer id) {
        flightService.deleteFlight(id);
    }

}
