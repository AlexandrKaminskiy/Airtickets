package com.company.innowise.airticketsapp.businessservice.controller;

import com.company.innowise.airticketsapp.businessservice.dto.FlightDto;
import com.company.innowise.airticketsapp.businessservice.dto.NewFlightDto;
import com.company.innowise.airticketsapp.businessservice.dto.UpdatedFlightDto;
import com.company.innowise.airticketsapp.businessservice.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.company.innowise.airticketsapp.businessservice.model.Flight_.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/")
    public List<FlightDto> getAll(@RequestParam(defaultValue = "10", required = false) Integer size,
                                  @RequestParam(defaultValue = "0", required = false) Integer page,
                                  @RequestParam(required = false) String to,
                                  @RequestParam(required = false) String from,
                                  @RequestParam(required = false) LocalDateTime timeDeparture,
                                  @RequestParam(required = false) LocalDateTime timeArrive) {

        Map<String, Object> params = new HashMap<>();
        params.compute(TO, (k, v)-> to);
        params.compute(FROM, (k, v)-> from);
        params.compute(TIME_DEPARTURE, (k, v)-> timeDeparture);
        params.compute(TIME_ARRIVE, (k, v)-> timeArrive);
        return flightService.getAll(params, size, page);
    }

    @GetMapping("/{id}")
    public FlightDto getOne(@PathVariable Integer id) {
        return flightService.getFlight(id);
    }

    @PostMapping("/")
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
