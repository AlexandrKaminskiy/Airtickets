package com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl;

import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.AbstractSpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.ParameterValidator;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Optional;
import static com.company.innowise.airticketsapp.businessservice.model.Passenger_.*;

@Component
public class PassengerSpecificationBuilder implements AbstractSpecificationBuilder<Passenger> {

    @Override
    public <X> Specification<X> getSpecification(Optional<Join<X, Passenger>> join, Map<String, Object> parameters) {
        return ((root, query, criteriaBuilder) -> {
            From<X,?> from = join.isPresent() ? join.get() : root;
            return criteriaBuilder.and(
                    ParameterValidator.builder()
                            .add(criteriaBuilder.equal(from.get(FIRSTNAME),
                                    parameters.get(FIRSTNAME)),
                                    Optional.ofNullable(parameters.get(FIRSTNAME)))
                            .add(criteriaBuilder.equal(from.get(LASTNAME),
                                    parameters.get(LASTNAME)),
                                    Optional.ofNullable(parameters.get(LASTNAME)))
                            .add(criteriaBuilder.equal(from.get(USERNAME),
                                    parameters.get(USERNAME)),
                                    Optional.ofNullable(parameters.get(USERNAME)))
                            .build());
        });
    }

}
