package com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl;

import com.company.innowise.airticketsapp.businessservice.model.Flight;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.AbstractSpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.ParameterValidator;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import static com.company.innowise.airticketsapp.businessservice.model.Flight_.TIME_ARRIVE;
import static com.company.innowise.airticketsapp.businessservice.model.Flight_.TIME_DEPARTURE;
import static com.company.innowise.airticketsapp.businessservice.model.PrimaryEntity_.ID;

@Component
public class FlightSpecificationBuilder implements AbstractSpecificationBuilder<Flight> {

    @Override
    public <X> Specification<X> getSpecification(Optional<Join<X, Flight>> xFlightJoin, Map<String, Object> parameters) {
        return ((root, query, criteriaBuilder) -> {
            From<X,?> from = xFlightJoin.isPresent() ? xFlightJoin.get() : root;

            return criteriaBuilder.and(
                    ParameterValidator.builder()
                            .add(criteriaBuilder.greaterThanOrEqualTo(from.get(TIME_ARRIVE),
                                    (LocalDateTime) parameters.get(TIME_ARRIVE)),
                                    Optional.ofNullable(parameters.get(TIME_ARRIVE)))
                            .add(criteriaBuilder.lessThanOrEqualTo(from.get(TIME_DEPARTURE),
                                    (LocalDateTime) parameters.get(TIME_DEPARTURE)),
                                    Optional.ofNullable(parameters.get(TIME_DEPARTURE)))
                            .add(criteriaBuilder.equal(from.get(ID),
                                            parameters.get(ID)),
                                    Optional.ofNullable(parameters.get(ID)))
                            .build());
        });
    }

}
