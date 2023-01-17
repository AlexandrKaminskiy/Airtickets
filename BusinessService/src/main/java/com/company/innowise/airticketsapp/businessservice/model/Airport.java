package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Airport extends PrimaryEntity {

    private String name;

    private String country;

    private String town;

}