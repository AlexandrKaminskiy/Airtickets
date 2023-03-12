package com.company.innowise.airticketsapp.businessservice.controller;

import com.company.innowise.airticketsapp.businessservice.dto.PassengerDto;
import com.company.innowise.airticketsapp.businessservice.model.Role;
import com.company.innowise.airticketsapp.businessservice.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/")
    public List<PassengerDto> getPassengers(@PageableDefault Pageable pageable,
                                            @RequestParam(required = false) String username,
                                            @RequestParam(required = false) String firstname,
                                            @RequestParam(required = false) String lastname) {

        Map<String, Object> params = new HashMap<>();
        params.compute(USERNAME, (k, v) -> username);
        params.compute(FIRSTNAME, (k, v) -> firstname);
        params.compute(LASTNAME, (k, v) -> lastname);

        return passengerService.getAll(params, pageable);
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