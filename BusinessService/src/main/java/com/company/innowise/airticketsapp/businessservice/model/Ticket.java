package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket extends PrimaryEntity {

    //make cascade only for tests
    @ManyToOne(cascade = CascadeType.ALL)
    private Airport from;

    @ManyToOne(cascade = CascadeType.ALL)
    private Airport to;

    private LocalDateTime timeDeparture;

    private LocalDateTime timeArrive;

    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.ALL)
    private Company company;

    @ManyToOne(cascade = CascadeType.ALL)
    private Passenger passenger;

}