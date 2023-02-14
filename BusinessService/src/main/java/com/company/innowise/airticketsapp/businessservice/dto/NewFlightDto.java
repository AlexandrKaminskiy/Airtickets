package com.company.innowise.airticketsapp.businessservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewFlightDto {

    private Integer seatsCount;
    private LocalDateTime timeDeparture;
    private LocalDateTime timeArrive;
    private Double price;
    private Integer fromId;
    private Integer toId;

}
