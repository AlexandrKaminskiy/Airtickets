package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket extends PrimaryEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Airport from;

    @ManyToOne
    private Airport to;

    private LocalDate timeDeparture;

    private LocalDate timeArrive;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    private Passenger passenger;

}