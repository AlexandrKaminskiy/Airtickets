package com.company.innowise.airticketsapp.businessservice.repository.quetyUtils;

import com.company.innowise.airticketsapp.businessservice.model.Airport_;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.expression.common.ExpressionUtils;

import java.util.ArrayList;
import java.util.List;


public class SpecificationBuilder {

    private final List<Predicate> predicates;

    private SpecificationBuilder() {
        predicates = new ArrayList<>();
    }

    public SpecificationBuilder add(Predicate predicate, Object value) {
        if (value != null) {

            predicates.add(predicate);
        }
        return this;
    }

    public Predicate[] build() {
        return predicates.toArray(new Predicate[0]);
    }

    public static SpecificationBuilder builder() {
        return new SpecificationBuilder();
    }
}
