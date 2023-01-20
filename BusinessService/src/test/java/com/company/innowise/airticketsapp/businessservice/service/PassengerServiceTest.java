package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PassengerServiceTest {

    private final PassengerService passengerService;

    @Autowired
    public PassengerServiceTest(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @Test
    void getAll() {

    }

    @Test
    void testGetAll() {
    }

    @Test
    void getPassenger() {
        Passenger passenger = new Passenger();
        passenger.setBirthdate(LocalDate.now());
        passenger.setFirstname("alexandr");
        passenger.setLastname("kaminskiy");
        passenger.setRole(Role.ADMIN);
        passenger.setPassport("AB1234567");

    }

    @Test
    void addPassenger() {
    }

    @Test
    void deletePassenger() {
    }
}