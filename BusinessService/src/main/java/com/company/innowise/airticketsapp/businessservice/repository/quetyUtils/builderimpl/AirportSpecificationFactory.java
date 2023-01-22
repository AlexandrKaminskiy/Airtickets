package com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.builderimpl;

import com.company.innowise.airticketsapp.businessservice.model.Airport;
import com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.AbstractSpecificationFactory;
import com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.ParameterValidator;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class AirportSpecificationFactory implements AbstractSpecificationFactory<Airport> {

    @Override
    public <X> Specification<X> getSpecification(Optional<Join<X, Airport>> join, Map<String, Object> parameters) {

        return ((root, query, criteriaBuilder) -> {
            From<X,?> from = join.isPresent() ? join.get() : root;
            return criteriaBuilder.and(
                    ParameterValidator.builder()
                            .add(criteriaBuilder.equal(from.get("town"), parameters.get("town")), parameters.get("town"))
                            .add(criteriaBuilder.equal(from.get("country"), parameters.get("country")), parameters.get("country"))
                            .add(criteriaBuilder.equal(from.get("name"), parameters.get("name")), parameters.get("name"))
                            .build());
        });
    }

}
