package com.company.innowise.airticketsapp.businessservice.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Company extends PrimaryEntity {

    String name;

}