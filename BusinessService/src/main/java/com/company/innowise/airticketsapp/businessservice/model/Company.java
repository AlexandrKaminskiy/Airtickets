package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "company")
public class Company extends PrimaryEntity {
    String name;
    @OneToMany(mappedBy = "company")
    private List<Ticket> ticket;

}