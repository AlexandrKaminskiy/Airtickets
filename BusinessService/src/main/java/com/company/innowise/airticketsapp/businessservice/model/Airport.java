package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "airport")
public class Airport {
    @EmbeddedId
    private AirportEmbeddedId airportEmbeddedId;

}