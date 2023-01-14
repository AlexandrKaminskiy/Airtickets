package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
public class PrimaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

}