package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
public class Passenger extends PrimaryEntity {

    private String email;

    private String password;

    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String passport;

    private String firstname;

    private String lastname;

    private LocalDate birthdate;

    @OneToMany(mappedBy = "passenger")
    private List<Ticket> ticket;

}