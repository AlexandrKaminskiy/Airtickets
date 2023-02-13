package com.company.innowise.airticketsapp.businessservice.mapper.impl;

import com.company.innowise.airticketsapp.businessservice.dto.PassengerDto;
import com.company.innowise.airticketsapp.businessservice.mapper.Mapper;
import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PassengerMapper implements Mapper<Passenger, PassengerDto> {

    private ModelMapper modelMapper;

    public PassengerMapper() {
        modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Passenger.class, PassengerDto.class);
        modelMapper.createTypeMap(PassengerDto.class, Passenger.class);
    }

    @Override
    public Passenger toModel(PassengerDto passengerDto) {
        return modelMapper.map(passengerDto, Passenger.class);
    }

    @Override
    public PassengerDto toDto(Passenger passenger) {
        return modelMapper.map(passenger, PassengerDto.class);
    }
}
