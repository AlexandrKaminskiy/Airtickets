package com.company.innowise.airticketsapp.businessservice.controller;

import com.company.innowise.airticketsapp.businessservice.dto.TicketDto;
import com.company.innowise.airticketsapp.businessservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/flight/{flightId}")
    public List<TicketDto> getAll(@PathVariable Integer flightId,
                                  @RequestParam(defaultValue = "10", required = false) Integer size,
                                  @RequestParam(defaultValue = "0", required = false) Integer page,
                                  @RequestParam(required = false) Double priceFrom,
                                  @RequestParam(required = false) Double priceTo) {

        Map<String, Object> params = new HashMap<>();
        params.compute("flightId", (k, v) -> flightId);
        params.compute("priceFrom", (k, v) -> priceFrom);
        params.compute("priceTo", (k, v) -> priceTo);
        return ticketService.getAll(params, size, page);
    }

    @GetMapping("/{id}")
    public TicketDto getTicket(@PathVariable Long id) {
        return ticketService.getTicket(id);
    }

}
