package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "id_generator", sequenceName = "passenger_seq", allocationSize = 1)
public class Passenger extends PrimaryEntity {

    private String email;

    private String password;

    private String username;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<Role> roles;

    private String passport;

    private String firstname;

    private String lastname;

    private LocalDate birthdate;

    @OneToMany(mappedBy = "passenger")
    private List<Ticket> tickets;

}