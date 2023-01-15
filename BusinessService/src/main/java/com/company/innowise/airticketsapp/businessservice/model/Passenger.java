package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "passenger")
public class Passenger extends PrimaryEntity {

    private String passport;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;


    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(name="passenger_ticket",
            joinColumns = @JoinColumn(name = "passenger_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    private List<Ticket> ticket;

    @ManyToMany(mappedBy = "passenger")
    private List<Client> clients;

}