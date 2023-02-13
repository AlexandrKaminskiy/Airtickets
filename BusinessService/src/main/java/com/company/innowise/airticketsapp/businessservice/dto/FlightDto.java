package com.company.innowise.airticketsapp.businessservice.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.company.innowise.airticketsapp.businessservice.model.Flight} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto implements Serializable {
    private AirportDto from;
    private AirportDto to;
    private LocalDateTime timeDeparture;
    private LocalDateTime timeArrive;
}