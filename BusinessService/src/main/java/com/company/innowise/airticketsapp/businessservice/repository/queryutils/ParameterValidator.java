package com.company.innowise.airticketsapp.businessservice.repository.queryutils;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParameterValidator {

    private final List<Predicate> predicates;

    private ParameterValidator() {
        predicates = new ArrayList<>();
    }

    public ParameterValidator add(Predicate predicate, Optional<?> value) {
        value.ifPresent(o -> predicates.add(predicate));
        return this;
    }

    public Predicate[] build() {
        return predicates.toArray(new Predicate[0]);
    }

    public static ParameterValidator builder() {
        return new ParameterValidator();
    }

}
