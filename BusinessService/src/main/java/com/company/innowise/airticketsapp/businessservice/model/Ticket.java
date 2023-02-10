package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends PrimaryEntity {

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    private Flight flight;
}