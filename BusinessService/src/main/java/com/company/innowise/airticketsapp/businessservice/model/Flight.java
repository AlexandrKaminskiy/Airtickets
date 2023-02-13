package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.*;
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
@SequenceGenerator(name = "id_generator", sequenceName = "flight_seq", allocationSize = 1)
public class Flight extends PrimaryEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Airport from;

    @ManyToOne(fetch = FetchType.LAZY)
    private Airport to;

    private int seatsCount;

    private LocalDateTime timeDeparture;

    private LocalDateTime timeArrive;

    @OneToMany(mappedBy = "flight")
    private List<Ticket> tickets;

}
