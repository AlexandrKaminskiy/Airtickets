package com.company.innowise.airticketsapp.businessservice.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.company.innowise.airticketsapp.businessservice.model.Ticket} entity
 */
public record TicketDto(@NotNull AirportDto from,
                        @NotNull AirportDto to,
                        @DateTimeFormat @NotNull LocalDateTime timeDeparture,
                        @DateTimeFormat @NotNull LocalDateTime timeArrive,
                        @NotNull BigDecimal price,
                        @NotNull CompanyDto company) implements Serializable {
}