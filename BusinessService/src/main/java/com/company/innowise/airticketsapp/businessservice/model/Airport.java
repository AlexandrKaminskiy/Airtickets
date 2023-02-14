package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "id_generator", sequenceName = "airport_seq", allocationSize = 1)
public class Airport extends PrimaryEntity {

    private String name;
    private String country;
    private String town;

}