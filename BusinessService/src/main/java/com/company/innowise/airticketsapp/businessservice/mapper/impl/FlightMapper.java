package com.company.innowise.airticketsapp.businessservice.mapper.impl;

import com.company.innowise.airticketsapp.businessservice.dto.FlightDto;
import com.company.innowise.airticketsapp.businessservice.mapper.Mapper;
import com.company.innowise.airticketsapp.businessservice.model.Flight;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper implements Mapper<Flight, FlightDto> {

    private ModelMapper modelMapper;

    public FlightMapper() {
        modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Flight.class, FlightDto.class);

        modelMapper.createTypeMap(FlightDto.class, Flight.class);
    }

    @Override
    public Flight toModel(FlightDto flightDto) {
        return modelMapper.map(flightDto, Flight.class);
    }

    @Override
    public FlightDto toDto(Flight flight) {
        return modelMapper.map(flight, FlightDto.class);
    }
}
