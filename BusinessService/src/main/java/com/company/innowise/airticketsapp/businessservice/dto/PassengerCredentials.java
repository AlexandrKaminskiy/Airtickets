package com.company.innowise.airticketsapp.businessservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerCredentials {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
