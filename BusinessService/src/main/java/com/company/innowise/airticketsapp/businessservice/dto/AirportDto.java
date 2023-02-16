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
public class AirportDto {

    @NotNull
    private String name;

    @NotNull
    private String country;

    @NotNull
    private String town;

}