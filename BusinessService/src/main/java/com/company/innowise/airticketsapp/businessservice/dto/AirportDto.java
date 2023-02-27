package com.company.innowise.airticketsapp.businessservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirportDto {

    @NotNull
    @Length(min = 1)
    private String name;

    @NotNull
    @Length(min = 1)
    private String country;

    @NotNull
    @Length(min = 1)
    private String town;

}