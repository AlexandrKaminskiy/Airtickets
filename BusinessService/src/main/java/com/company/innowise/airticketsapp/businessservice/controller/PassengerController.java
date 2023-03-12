package com.company.innowise.airticketsapp.businessservice.controller;

import com.company.innowise.airticketsapp.businessservice.dto.PassengerDto;
import com.company.innowise.airticketsapp.businessservice.findingdto.PassengerFindingDto;
import com.company.innowise.airticketsapp.businessservice.model.Role;
import com.company.innowise.airticketsapp.businessservice.service.PassengerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.company.innowise.airticketsapp.businessservice.model.Passenger_.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/passenger")
public class PassengerController {

    private final PassengerService passengerService;

    @PostMapping("/all")
    public List<PassengerDto> getPassengers(@RequestBody @Valid PassengerFindingDto passengerFindingDto) {

        Map<String, Object> params = new HashMap<>();
        params.compute(USERNAME, (k, v) -> passengerFindingDto.getUsername());
        params.compute(FIRSTNAME, (k, v) -> passengerFindingDto.getFirstname());
        params.compute(LASTNAME, (k, v) -> passengerFindingDto.getLastname());

        return passengerService.getAll(params, passengerFindingDto.getPageRequest());
    }

    @PutMapping("/change-role/{username}")
    public Set<Role> changeRole(@PathVariable String username,
                           @RequestBody Set<Role> roles) {
        return passengerService.changeRole(username, roles);
    }

    @GetMapping("/{username}")
    public PassengerDto getPassenger(@PathVariable String username) {
        return passengerService.getPassenger(username);
    }

}