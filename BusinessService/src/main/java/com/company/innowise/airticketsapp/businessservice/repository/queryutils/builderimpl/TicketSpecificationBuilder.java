package com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl;

import com.company.innowise.airticketsapp.businessservice.model.Ticket;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.AbstractSpecificationBuilder;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.ParameterValidator;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Optional;
import static com.company.innowise.airticketsapp.businessservice.model.Ticket_.PRICE;

@Component
public class TicketSpecificationBuilder implements AbstractSpecificationBuilder<Ticket> {

    @Override
    public <X> Specification<X> getSpecification(Optional<Join<X, Ticket>> extRoot, Map<String, Object> parameters) {
        return ((root, query, criteriaBuilder) -> {
            From<X,?> from = extRoot.isPresent() ? extRoot.get() : root;
            return criteriaBuilder.and(
                    ParameterValidator.builder()
                            .add(criteriaBuilder.equal(from.get(PRICE),
                                    parameters.get(PRICE)),
                                    Optional.of(parameters.get(PRICE)))
                            .build());
        });
    }

}
