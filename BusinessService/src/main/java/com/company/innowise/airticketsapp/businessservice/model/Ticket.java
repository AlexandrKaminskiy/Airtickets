package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Currency;

@Getter
@Setter
@Entity
public class Ticket extends PrimaryEntity {

    @ManyToOne
    private Airport from;

    @ManyToOne
    private Airport to;

    private LocalDate timeDeparture;

    private LocalDate timeArrive;

    private Currency price;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Passenger passenger;

}