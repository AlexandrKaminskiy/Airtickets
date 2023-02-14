package com.company.innowise.airticketsapp.businessservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link com.company.innowise.airticketsapp.businessservice.model.Airport} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirportDto implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private String country;

    @NotNull
    private String town;

}