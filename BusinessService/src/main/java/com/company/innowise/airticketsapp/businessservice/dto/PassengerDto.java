package com.company.innowise.airticketsapp.businessservice.dto;

import com.company.innowise.airticketsapp.businessservice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * A DTO for the {@link com.company.innowise.airticketsapp.businessservice.model.Passenger} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto implements Serializable {

    private String email;
    private String password;
    private String username;
    private Set<Role> roles;
    private String passport;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private List<TicketDto> tickets;

}