package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "client")
public class Client extends PrimaryEntity {

    private String email;
    private String password;
    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(name="client_passenger",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id"))
    private List<Passenger> passenger;

}