package com.company.innowise.airticketsapp.businessservice.mapper.impl;

import com.company.innowise.airticketsapp.businessservice.dto.AirportDto;
import com.company.innowise.airticketsapp.businessservice.mapper.Mapper;
import com.company.innowise.airticketsapp.businessservice.model.Airport;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper implements Mapper<Airport, AirportDto> {

    private ModelMapper modelMapper;

    public AirportMapper() {
        modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Airport.class, AirportDto.class);
        modelMapper.createTypeMap(AirportDto.class, Airport.class);
    }

    @Override
    public Airport toModel(AirportDto airportDto) {
        return modelMapper.map(airportDto, Airport.class);
    }

    @Override
    public AirportDto toDto(Airport airport) {
        return modelMapper.map(airport, AirportDto.class);
    }

}
