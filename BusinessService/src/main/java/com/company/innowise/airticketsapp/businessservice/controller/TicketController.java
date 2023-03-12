package com.company.innowise.airticketsapp.businessservice.controller;

import com.company.innowise.airticketsapp.businessservice.dto.TicketDto;
import com.company.innowise.airticketsapp.businessservice.findingdto.TicketFindingDto;
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

    @PostMapping("/flight/{flightId}")
    public List<TicketDto> getAll(@PathVariable Integer flightId,
                                  @RequestBody TicketFindingDto ticketFindingDto) {

        Map<String, Object> params = new HashMap<>();

        params.compute("flightId", (k, v) -> flightId);
        params.compute("priceFrom", (k, v) -> ticketFindingDto.getPriceFrom());
        params.compute("priceTo", (k, v) -> ticketFindingDto.getPriceTo());

        return ticketService.getAll(params, ticketFindingDto.getPageRequest());

    }

    @GetMapping("/{id}")
    public TicketDto getTicket(@PathVariable Long id) {
        return ticketService.getTicket(id);
    }

}
