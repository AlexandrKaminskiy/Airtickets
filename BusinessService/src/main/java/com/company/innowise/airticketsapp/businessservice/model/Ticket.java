package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Currency;
import java.util.List;

@Data
@Entity
@Table(name = "ticket")
public class Ticket extends PrimaryEntity {
    @ManyToOne(optional = false, cascade = { CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private Airport from;

    @ManyToOne(optional = false, cascade = { CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private Airport to;

    private LocalDate timeDeparture;
    private LocalDate timeArrive;
    private boolean isAdult;
    private int quantity;
    private Currency price;
    @ManyToOne(optional = false, cascade = { CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(mappedBy = "ticket")
    private List<Passenger> passenger;

}