package com.company.innowise.airticketsapp.businessservice.controller;

import com.company.innowise.airticketsapp.businessservice.dto.PassengerCredentials;
import com.company.innowise.airticketsapp.businessservice.dto.Token;
import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.model.Role;
import com.company.innowise.airticketsapp.businessservice.repository.PassengerRepository;
import com.company.innowise.airticketsapp.businessservice.service.PassengerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PassengerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private PassengerRepository repository;

    private Passenger passenger;

    @BeforeEach
    @Transactional
    public void setup() {

        passenger = new Passenger();
        passenger.setRoles(new HashSet<>(Set.of(Role.ROLE_PASSENGER, Role.ROLE_MANAGER)));
        passenger.setUsername("test_user");
        passenger.setPassword(passwordEncoder.encode("test_password"));
        passenger.setIsActive(true);
        repository.save(passenger);

    }
    @Test
    void getPassengerForbidden() throws Exception {

        Token token = passengerService.signIn(new PassengerCredentials("test_user", "test_password"));
        mockMvc.perform(get("/api/passenger/0").header(AUTHORIZATION, "Bearer " + token.getAccessToken()))
                .andExpect(status().isForbidden());

    }

    @AfterEach
    @Transactional
    public void removePassenger() {
        repository.delete(passenger);
    }

}