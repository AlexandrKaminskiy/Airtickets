package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.BusinessServiceApplication;
import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.model.Role;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest(classes = BusinessServiceApplication.class)
class PassengerServiceTest {

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
    @Transactional
    void getPassenger() {
//        Passenger passenger = passengerService.getPassenger(0L);
//        Assertions.assertEquals("test@gmail.com", passenger.getEmail());
    }

    @Test
    void addPassenger() {
//        Passenger passenger = passengerService.addPassenger(new Passenger("test@gmail.com",
//                "babubababa",
//                "babuba",
//                new HashSet<>(Set.of(Role.ADMIN)),
//                "AB1234567",
//                "Alexandr",
//                "Kaminskiy",
//                LocalDate.of(2003, 7, 4),
//                null));
//        Assertions.assertNotNull(passenger);
    }

    @Test
    void deletePassenger() {
    }
}