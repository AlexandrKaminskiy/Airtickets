package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class AirportEmbeddedId implements Serializable {
    private static final long serialVersionUID = 6783869895788342243L;
    private String name;
    @ManyToOne(optional = false,cascade = { CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH } )
    private Town town;
}