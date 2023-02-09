package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Set;

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
    private Set<Ticket> tickets;

}
