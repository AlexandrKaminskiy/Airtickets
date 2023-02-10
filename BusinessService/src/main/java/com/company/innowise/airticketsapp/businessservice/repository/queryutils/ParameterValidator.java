package com.company.innowise.airticketsapp.businessservice.repository.queryutils;

import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.company.innowise.airticketsapp.businessservice.model.Flight_.TIME_ARRIVE;
import static com.company.innowise.airticketsapp.businessservice.model.Flight_.TIME_DEPARTURE;

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
