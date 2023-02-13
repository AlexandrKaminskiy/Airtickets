package com.company.innowise.airticketsapp.businessservice.dto;

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
    private String name;
    private String country;
    private String town;
}