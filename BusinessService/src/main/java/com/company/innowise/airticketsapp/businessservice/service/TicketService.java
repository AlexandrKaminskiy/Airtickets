package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.model.Airport;
import com.company.innowise.airticketsapp.businessservice.model.Airport_;
import com.company.innowise.airticketsapp.businessservice.model.Ticket_;
import com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class TicketService {

    private Specification<Airport> getSpecification(Map<String,Object> parameters) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.and(
                SpecificationBuilder.builder()
                        .add(criteriaBuilder.greaterThanOrEqualTo(root.<LocalDate>get(Ticket_.TIME_ARRIVE), (LocalDate) parameters.get("from")), parameters.get("from"))
                        .add(criteriaBuilder.lessThanOrEqualTo(root.get(Ticket_.TIME_DEPARTURE), (LocalDate) parameters.get("to")), parameters.get("to"))
                        .add(criteriaBuilder.equal(root.get(Airport_.country), parameters.get("country")), parameters.get("country"))
                        .add(criteriaBuilder.equal(root.get(Airport_.name), parameters.get("name")), parameters.get("name`"))
                        .build()));
    }

}
