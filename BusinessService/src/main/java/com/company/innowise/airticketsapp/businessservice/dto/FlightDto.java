package com.company.innowise.airticketsapp.businessservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

    private Integer id;
    private AirportDto from;
    private AirportDto to;
    private int seatsCount;
    private LocalDateTime timeDeparture;
    private LocalDateTime timeArrive;


}