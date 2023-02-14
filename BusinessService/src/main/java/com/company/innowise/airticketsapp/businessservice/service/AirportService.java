package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.dto.AirportDto;
import com.company.innowise.airticketsapp.businessservice.mapper.impl.AirportMapper;
import com.company.innowise.airticketsapp.businessservice.model.Airport;
import com.company.innowise.airticketsapp.businessservice.repository.AirportRepository;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.AirportSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
    private final AirportSpecificationBuilder specificationBuilder;
    private final AirportMapper airportMapper;

    public List<Airport> getAll(Map<String, Object> parameters, int size, int page) {
        Specification<Airport> specification = getSpecification(parameters);
        return airportRepository.findAll(specification, Pageable.ofSize(size).withPage(page)).toList();
    }

    public Airport getAirport(long id) {
        return airportRepository.getReferenceById(id);
    }

    public Airport getById(long id) {
        return airportRepository.getReferenceById(id);
    }

    public AirportDto addAirport(AirportDto airportDto) {
        Airport airport = airportMapper.toModel(airportDto);
        airportRepository.save(airport);
        return airportDto;
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
