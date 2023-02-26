package com.company.innowise.airticketsapp.businessservice.controller;

import com.company.innowise.airticketsapp.businessservice.dto.NewPassengerDto;
import com.company.innowise.airticketsapp.businessservice.dto.PassengerCredentials;
import com.company.innowise.airticketsapp.businessservice.dto.Token;
import com.company.innowise.airticketsapp.businessservice.service.PassengerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final PassengerService passengerService;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody @Valid NewPassengerDto passengerDto) {
        return passengerService.signUp(passengerDto);
    }

    @PostMapping("/sign-in")
    public Token signIn(@RequestBody @Valid PassengerCredentials passengerCredentials) {
        return passengerService.signIn(passengerCredentials);
    }

    @GetMapping("/activate/{uuid}")
    public String signUp(@PathVariable String uuid) {
        return passengerService.activatePassenger(uuid);
    }

}