package com.company.innowise.airticketsapp.businessservice.mapper.impl;

import com.company.innowise.airticketsapp.businessservice.dto.NewPassengerDto;
import com.company.innowise.airticketsapp.businessservice.mapper.Mapper;
import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NewPassengerMapper implements Mapper<Passenger, NewPassengerDto> {

    private final ModelMapper modelMapper;

    public NewPassengerMapper() {

        modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Passenger.class, NewPassengerDto.class);
        modelMapper.createTypeMap(NewPassengerDto.class, Passenger.class);

    }

    @Override
    public Passenger toModel(NewPassengerDto dto) {
        return modelMapper.map(dto, Passenger.class);
    }

    @Override
    public NewPassengerDto toDto(Passenger passenger) {
        return modelMapper.map(passenger, NewPassengerDto.class);
    }

}
