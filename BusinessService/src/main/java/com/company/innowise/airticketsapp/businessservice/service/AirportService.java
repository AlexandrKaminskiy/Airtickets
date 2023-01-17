package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.model.Airport;
import com.company.innowise.airticketsapp.businessservice.model.Airport_;
import com.company.innowise.airticketsapp.businessservice.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    public List<Airport> getAll() {
        return airportRepository.findAll();
    }

    public List<Airport> getAll(Map<String, Object> parameters) {
        Specification<Airport> specification = getSpecification(parameters);
        return airportRepository.findAll(specification);
    }

    public Airport addAirport(Airport airport) {
        airportRepository.save(airport);
        return airport;
    }

    public void deleteAirport(long id) {
        airportRepository.deleteById(id);
    }

    private Specification<Airport> getSpecification(Map<String,Object> parameters) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get(Airport_.town), parameters.get("town")),
                criteriaBuilder.equal(root.get(Airport_.country), parameters.get("country")),
                criteriaBuilder.equal(root.get(Airport_.name), parameters.get("name")),
                criteriaBuilder.equal(root.get(Airport_.id), parameters.get("id"))
        ));
    }
}