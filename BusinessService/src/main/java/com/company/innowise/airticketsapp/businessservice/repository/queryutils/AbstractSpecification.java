package com.company.innowise.airticketsapp.businessservice.repository.queryutils;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import java.util.Map;
import java.util.Optional;

public interface AbstractSpecification<T> {

    <X> Specification<X> getSpecification(Optional<Join<X, T>> join, Map<String, Object> parameters);

}
