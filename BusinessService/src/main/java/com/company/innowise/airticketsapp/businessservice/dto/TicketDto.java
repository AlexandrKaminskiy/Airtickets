package com.company.innowise.airticketsapp.businessservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.company.innowise.airticketsapp.businessservice.model.Ticket} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto implements Serializable {

    private Integer id;
    private BigDecimal price;
    private PassengerDto passenger;
    private FlightDto flight;


}