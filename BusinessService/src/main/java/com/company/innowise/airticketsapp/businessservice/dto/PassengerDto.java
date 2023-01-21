package com.company.innowise.airticketsapp.businessservice.dto;

import com.company.innowise.airticketsapp.businessservice.model.Role;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * A DTO for the {@link com.company.innowise.airticketsapp.businessservice.model.Passenger} entity
 */
public record PassengerDto(String email, String password, String username, Role role, String firstname, String lastname,
                           LocalDate birthdate, List<TicketDto> ticket) implements Serializable {
}