package com.company.innowise.airticketsapp.businessservice.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * A DTO for the {@link com.company.innowise.airticketsapp.businessservice.model.Airport} entity
 */
public record AirportDto(@NotNull String name, @NotNull String country, @NotNull String town) {
}