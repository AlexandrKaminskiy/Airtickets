package com.company.innowise.airticketsapp.businessservice.repository.queryutils.specificationimpl;

import com.company.innowise.airticketsapp.businessservice.model.Airport;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.AbstractSpecification;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.ParameterValidator;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Optional;
import static com.company.innowise.airticketsapp.businessservice.model.Airport_.*;

@Component
public class AirportSpecification implements AbstractSpecification<Airport> {

    @Override
    public <X> Specification<X> getSpecification(Optional<Join<X, Airport>> join, Map<String, Object> parameters) {
        return ((root, query, criteriaBuilder) -> {
            From<X,?> from = join.isPresent() ? join.get() : root;
            return criteriaBuilder.and(
                    ParameterValidator.builder()
                            .add(criteriaBuilder.equal(from.get(TOWN),
                                    parameters.get(TOWN)),
                                    Optional.ofNullable(parameters.get(TOWN)))
                            .add(criteriaBuilder.equal(from.get(COUNTRY),
                                    parameters.get(COUNTRY)),
                                    Optional.ofNullable(parameters.get(COUNTRY)))
                            .add(criteriaBuilder.equal(from.get(NAME),
                                    parameters.get(NAME)),
                                    Optional.ofNullable(parameters.get(NAME)))
                            .build());
        });
    }

}
