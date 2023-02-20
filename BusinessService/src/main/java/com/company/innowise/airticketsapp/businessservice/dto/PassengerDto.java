package com.company.innowise.airticketsapp.businessservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

    private String email;
    private String password;
    private String username;
    private String passport;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;

}