package com.company.innowise.airticketsapp.businessservice.repository.quetyUtils;

import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;


public class ParameterValidator {

    private final List<Predicate> predicates;

    private ParameterValidator() {
        predicates = new ArrayList<>();
    }

    public ParameterValidator add(Predicate predicate, Object value) {
        if (value != null) {
            predicates.add(predicate);
        }
        return this;
    }

    public Predicate[] build() {
        return predicates.toArray(new Predicate[0]);
    }

    public static ParameterValidator builder() {
        return new ParameterValidator();
    }
}
