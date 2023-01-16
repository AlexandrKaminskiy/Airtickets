package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Airport extends PrimaryEntity {

    private String name;

    private String country;

    private String town;

}