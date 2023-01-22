package com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.builderimpl;

import com.company.innowise.airticketsapp.businessservice.model.Company;
import com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.AbstractSpecificationFactory;
import com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.ParameterValidator;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class CompanySpecificationFactory implements AbstractSpecificationFactory<Company> {

    @Override
    public <X> Specification<X> getSpecification(Optional<Join<X, Company>> xCompanyJoin, Map<String, Object> parameters) {
        return ((root, query, criteriaBuilder) -> {
            From<X,?> from = xCompanyJoin.isPresent() ? xCompanyJoin.get() : root;
            return criteriaBuilder.and(
                    ParameterValidator.builder()
                            .add(criteriaBuilder.equal(from.get("companyName"), parameters.get("companyName")), parameters.get("companyName"))
                            .build());
        });
    }

}
