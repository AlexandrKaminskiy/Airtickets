package com.company.innowise.airticketsapp.businessservice.repository.quetyUtils;

import org.springframework.data.jpa.domain.Specification;

public interface Builder {
    Specification getSpecification();
}
