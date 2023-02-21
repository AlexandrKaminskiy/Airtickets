package com.company.innowise.airticketsapp.businessservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewPassengerDto {

    private String email;
    private String password;
    private String username;
    private String passport;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;

}
