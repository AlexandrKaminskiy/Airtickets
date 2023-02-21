package com.company.innowise.airticketsapp.businessservice.controller;

import com.company.innowise.airticketsapp.businessservice.dto.NewPassengerDto;
import com.company.innowise.airticketsapp.businessservice.dto.PassengerCredentials;
import com.company.innowise.airticketsapp.businessservice.dto.Token;
import com.company.innowise.airticketsapp.businessservice.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final PassengerService passengerService;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody NewPassengerDto passengerDto) {
        return passengerService.signUp(passengerDto);
    }

    @PostMapping("/sign-in")
    public Token signIn(@RequestBody PassengerCredentials passengerCredentials) {
        return passengerService.signIn(passengerCredentials);
    }

}
