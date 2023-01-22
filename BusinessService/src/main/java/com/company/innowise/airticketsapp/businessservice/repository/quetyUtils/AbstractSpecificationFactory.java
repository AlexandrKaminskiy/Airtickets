package com.company.innowise.airticketsapp.businessservice.repository.quetyUtils;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;
import java.util.Optional;

public interface AbstractSpecificationFactory<T> {

    <X> Specification<X> getSpecification(Optional<Join<X, T>> join, Map<String, Object> parameters);

}
