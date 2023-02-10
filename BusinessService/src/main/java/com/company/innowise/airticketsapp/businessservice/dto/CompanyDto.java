package com.company.innowise.airticketsapp.businessservice.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * A DTO for the {@link com.company.innowise.airticketsapp.businessservice.model.Company} entity
 */

public record CompanyDto(@NotNull String companyName) {
}