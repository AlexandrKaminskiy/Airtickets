package com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.builderimpl;

import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.model.Passenger_;
import com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.AbstractSpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.ParameterValidator;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class PassengerSpecificationBuilder implements AbstractSpecificationBuilder<Passenger> {

    @Override
    public <X> Specification<X> getSpecification(Optional<Join<X, Passenger>> join, Map<String, Object> parameters) {
        return ((root, query, criteriaBuilder) -> {
            From<X,?> from = join.isPresent() ? join.get() : root;
            return criteriaBuilder.and(
                    ParameterValidator.builder()
                            .add(criteriaBuilder.equal(from.get(Passenger_.FIRSTNAME), parameters.get("firstname")), parameters.get("firstname"))
                            .add(criteriaBuilder.equal(from.get(Passenger_.LASTNAME), parameters.get("lastname")), parameters.get("lastname"))
                            .add(criteriaBuilder.equal(from.get(Passenger_.USERNAME), parameters.get("username")), parameters.get("username"))
                            .build());
        });
    }
}
