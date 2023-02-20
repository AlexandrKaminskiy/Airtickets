package com.company.innowise.airticketsapp.businessservice.controller;

import com.company.innowise.airticketsapp.businessservice.dto.PassengerDto;
import com.company.innowise.airticketsapp.businessservice.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/passenger")
public class PassengerController {

    private final PassengerService passengerService;

    @PostMapping("/signUp")
    public String signUp(@RequestBody PassengerDto passengerDto) {
        passengerService.signUp(passengerDto);
        return "s";
    }

    @PostMapping
    public String test() {
        return "qwe";
    }
}