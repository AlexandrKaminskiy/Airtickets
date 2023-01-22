package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.model.Airport;
import com.company.innowise.airticketsapp.businessservice.model.Airport_;
import com.company.innowise.airticketsapp.businessservice.repository.AirportRepository;
import com.company.innowise.airticketsapp.businessservice.repository.quetyUtils.builderimpl.AirportSpecificationFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
    private final AirportSpecificationFactory specificationBuilder;

    public List<Airport> getAll() {
        return airportRepository.findAll();
    }

    public List<Airport> getAll(Map<String, Object> parameters) {
        Specification<Airport> specification = getSpecification(parameters);
        return airportRepository.findAll(specification);
    }

    public Airport getAirport(Long id) {
        return airportRepository.getReferenceById(id);
    }

    public Airport addAirport(Airport airport) {
        airportRepository.save(airport);
        return airport;
    }

    public void deleteAirport(long id) {
        airportRepository.deleteById(id);
    }

    private Specification<Airport> getSpecification(Map<String, Object> parameters) {
        return (root, query, criteriaBuilder) -> {
            Specification<Airport> airportSpecification = specificationBuilder.getSpecification(Optional.empty(), parameters);
            return airportSpecification.toPredicate(root, query, criteriaBuilder);
        };
    }
}
