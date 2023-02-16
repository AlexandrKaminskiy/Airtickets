package com.company.innowise.airticketsapp.businessservice.dto;

import com.company.innowise.airticketsapp.businessservice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

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