package com.company.innowise.airticketsapp.businessservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private Integer id;
    private BigDecimal price;
    private PassengerDto passenger;
    private FlightDto flight;


}