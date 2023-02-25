package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.dto.AirportDto;
import com.company.innowise.airticketsapp.businessservice.exception.BusinessException;
import com.company.innowise.airticketsapp.businessservice.mapper.impl.AirportMapper;
import com.company.innowise.airticketsapp.businessservice.model.Airport;
import com.company.innowise.airticketsapp.businessservice.repository.AirportRepository;
import com.company.innowise.airticketsapp.businessservice.repository.queryutils.builderimpl.AirportSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
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

    public Airport getById(long id) {
        return airportRepository.findById(id).orElseThrow(()->new BusinessException("airport not found"));
    }

    @Transactional
    public AirportDto addAirport(AirportDto airportDto) {
        Airport airport = airportMapper.toModel(airportDto);
        airportRepository.save(airport);
        log.info("FLIGHT {} WAS ADDED", airport.getId());
        return airportDto;
    }

    @Transactional
    public void deleteAirport(long id) {
        Airport airport = airportRepository.findById(id).orElseThrow(() -> new BusinessException("airport not found"));
        airportRepository.delete(airport);
        log.info("FLIGHT {} WAS DELETED", airport.getId());
    }

    private Specification<Airport> getSpecification(Map<String, Object> parameters) {
        return (root, query, criteriaBuilder) -> {
            Specification<Airport> airportSpecification = specificationBuilder.getSpecification(Optional.empty(), parameters);
            return airportSpecification.toPredicate(root, query, criteriaBuilder);
        };
    }
}
