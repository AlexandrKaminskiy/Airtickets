package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class TownEmbeddedId implements Serializable {

    private static final long serialVersionUID = 2869092356837484959L;
    private String country;
    private String name;
}