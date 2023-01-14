package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "town")
public class Town {
    @EmbeddedId
    private TownEmbeddedId townEmbeddedId;

}