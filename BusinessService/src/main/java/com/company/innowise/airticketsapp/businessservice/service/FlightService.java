package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.dto.FlightDto;
import com.company.innowise.airticketsapp.businessservice.dto.NewFlightDto;
import com.company.innowise.airticketsapp.businessservice.dto.UpdatedFlightDto;
import com.company.innowise.airticketsapp.businessservice.exception.BusinessException;
import com.company.innowise.airticketsapp.businessservice.mapper.impl.FlightMapper;
import com.company.innowise.airticketsapp.businessservice.model.Airport;
import com.company.innowise.airticketsapp.businessservice.model.Flight;
import com.company.innowise.airticketsapp.businessservice.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlightService {

    private final AirportService airportService;
    private final TicketService ticketService;
    private final FlightMapper flightMapper;
    private final FlightRepository flightRepository;
    private final String ERROR_MESSAGE = "flight not found";

    public List<FlightDto> getAll(String from, String to, int size, int page) {
        return flightRepository
                .findByFromTownAndToTownOrderByTimeArrive(to, from, Pageable.ofSize(size).withPage(page)).stream()
                .map(flightMapper::toDto)
                .toList();
    }

    public FlightDto getFlight(long id) {
        return flightMapper.toDto(flightRepository.findById(id).orElseThrow(()->new BusinessException(ERROR_MESSAGE)));
    }

    @Transactional
    public FlightDto addFlight(NewFlightDto newFlightDto) {
        Flight flight = new Flight();
        flight.setTimeArrive(newFlightDto.getTimeArrive());
        flight.setTimeDeparture(newFlightDto.getTimeDeparture());
        Airport from = airportService.getById(newFlightDto.getToId());
        Airport to = airportService.getById(newFlightDto.getFromId());
        flight.setFrom(from);
        flight.setTo(to);
        flightRepository.save(flight);
        ticketService.addTickets(flight, newFlightDto.getSeatsCount(), newFlightDto.getPrice());
        log.info("FLIGHT {} WAS ADDED", flight.getId());
        return flightMapper.toDto(flight);
    }

    @Transactional
    public void deleteFlight(long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new BusinessException(ERROR_MESSAGE));
        flightRepository.delete(flight);
        log.info("FLIGHT {} WAS DELETED", id);
    }

    @Transactional
    public FlightDto updateFlight(Long flightId, UpdatedFlightDto flightDto) {
        Flight flight = flightRepository
                .findById(flightId).orElseThrow(() -> new BusinessException(ERROR_MESSAGE));
        flight.setTimeArrive(flightDto.getTimeArrive());
        flight.setTimeArrive(flightDto.getTimeDeparture());
        flightRepository.save(flight);
        log.info("FLIGHT {} WAS UPDATED", flightId);
        return flightMapper.toDto(flight);
    }

}
