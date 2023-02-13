package com.company.innowise.airticketsapp.businessservice.repository;

import com.company.innowise.airticketsapp.businessservice.model.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long>, JpaSpecificationExecutor<Airport> {
    @Override
    Page<Airport> findAll(Specification<Airport> spec, Pageable pageable);
    Page<Airport> findAll(Pageable pageable);
}
