package com.company.innowise.airticketsapp.businessservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private Integer id;
    private BigDecimal price;
    private FlightDto flight;

}