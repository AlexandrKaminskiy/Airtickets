package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Flight extends PrimaryEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Airport from;

    @ManyToOne(fetch = FetchType.LAZY)
    private Airport to;

    private LocalDateTime timeDeparture;

    private LocalDateTime timeArrive;

    @OneToMany(mappedBy = "flight")
    private List<Ticket> tickets;

}
