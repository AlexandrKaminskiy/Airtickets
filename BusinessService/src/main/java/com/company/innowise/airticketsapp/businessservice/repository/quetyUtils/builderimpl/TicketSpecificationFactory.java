package com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.builderimpl;

import com.company.innowise.airticketsapp.businessservice.model.Ticket;
import com.company.innowise.airticketsapp.businessservice.model.Ticket_;
import com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.AbstractSpecificationFactory;
import com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.ParameterValidator;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Component
public class TicketSpecificationFactory implements AbstractSpecificationFactory<Ticket> {

    @Override
    public <X> Specification<X> getSpecification(Optional<Join<X, Ticket>> extRoot, Map<String, Object> parameters) {
        return ((root, query, criteriaBuilder) -> {
            From<X,?> from = extRoot.isPresent() ? extRoot.get() : root;
            return criteriaBuilder.and(
                    ParameterValidator.builder()
                            .add(criteriaBuilder.greaterThanOrEqualTo(from.get(Ticket_.TIME_ARRIVE), (LocalDateTime) parameters.get("timeArrive")), parameters.get("timeArrive"))
                            .add(criteriaBuilder.lessThanOrEqualTo(from.get(Ticket_.TIME_DEPARTURE), (LocalDateTime) parameters.get("timeDeparture")), parameters.get("timeDeparture"))
                            .add(criteriaBuilder.equal(from.get(Ticket_.PRICE), parameters.get("price")), parameters.get("price"))
                            .build());
        });
    }

}
