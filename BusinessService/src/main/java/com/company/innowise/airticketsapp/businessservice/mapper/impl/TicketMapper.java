package com.company.innowise.airticketsapp.businessservice.mapper.impl;

import com.company.innowise.airticketsapp.businessservice.dto.TicketDto;
import com.company.innowise.airticketsapp.businessservice.mapper.Mapper;
import com.company.innowise.airticketsapp.businessservice.model.Ticket;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper implements Mapper<Ticket, TicketDto> {

    private final ModelMapper modelMapper;

    public TicketMapper() {
        modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Ticket.class, TicketDto.class);
        modelMapper.createTypeMap(TicketDto.class, Ticket.class);
    }

    @Override
    public Ticket toModel(TicketDto ticketDto) {
        return modelMapper.map(ticketDto, Ticket.class);
    }

    @Override
    public TicketDto toDto(Ticket ticket) {
        return modelMapper.map(ticket, TicketDto.class);
    }

}
