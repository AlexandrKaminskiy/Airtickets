package com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl;

import com.company.innowise.airticketsapp.businessservice.model.Flight;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.AbstractSpecification;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.ParameterValidator;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

import static com.company.innowise.airticketsapp.businessservice.model.PrimaryEntity_.ID;

@Component
public class FlightSpecification implements AbstractSpecification<Flight> {

    @Override
    public <X> Specification<X> getSpecification(Optional<Join<X, Flight>> xFlightJoin, Map<String, Object> parameters) {
        return ((root, query, criteriaBuilder) -> {
            From<X,?> from = xFlightJoin.isPresent() ? xFlightJoin.get() : root;
            String flightId = "flightId";
            return criteriaBuilder.and(
                    ParameterValidator.builder()
                            .add(criteriaBuilder.equal(from.get(ID),
                                    parameters.get(flightId)),
                                    Optional.ofNullable(parameters.get(flightId)))
                            .build());
        });
    }

}
