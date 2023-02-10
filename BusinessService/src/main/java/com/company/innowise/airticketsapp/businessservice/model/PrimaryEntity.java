package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class PrimaryEntity {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

}