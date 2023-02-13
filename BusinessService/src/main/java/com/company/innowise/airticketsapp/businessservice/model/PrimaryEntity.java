package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class PrimaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_generator")
    @Column(name = "id", nullable = false)
    private Integer id;

}