package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.dto.Activity;
import com.company.innowise.airticketsapp.businessservice.dto.UserInfo;
import com.company.innowise.airticketsapp.businessservice.exception.BusinessException;
import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.model.Ticket;
import com.company.innowise.airticketsapp.businessservice.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final TicketRepository ticketRepository;
    private final PassengerService passengerService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.exchange}")
    private String exchange;

    @Value("${rabbit.routing-key}")
    private String routingKey;

    @Transactional
    public void purchaseTicket(Long ticketId, Principal principal) {

        log.info("ATTEMPT TO BY TICKET WITH ID {}", ticketId);
        Passenger passenger = passengerService.getByUsername(principal.getName());
        Ticket ticket = ticketRepository
                .findById(ticketId).orElseThrow(() -> new BusinessException("ticket not found"));
        if (ticket.getPassenger() != null) {
            log.error("FAILED TO BYE TICKET WITH ID {}", ticketId);
            throw new BusinessException("You can't buy this ticket");
        }
        ticket.setPassenger(passenger);
        ticketRepository.save(ticket);
        CompletableFuture.runAsync(() -> rabbitTemplate.convertAndSend(exchange, routingKey,
                new UserInfo(passenger.getUsername(), LocalDateTime.now(), Activity.BUY_TICKET)));
        log.info("TICKET WITH ID {} WAS BOUGHT BY {}", ticketId, passenger.getUsername());

    }

    @Transactional
    public void sellTicket(Long ticketId, Principal principal) {

        log.info("ATTEMPT TO SELL TICKET WITH ID {}", ticketId);
        Passenger passenger = passengerService.getByUsername(principal.getName());
        Ticket ticket = ticketRepository
                .findById(ticketId).orElseThrow(() -> new BusinessException("ticket not found"));
        if (ticket.getPassenger() != passenger) {
            log.error("FAILED TO SELL TICKET WITH ID {}", ticketId);
            throw new BusinessException("You can't sell this ticket");
        }
        ticket.setPassenger(null);
        ticketRepository.save(ticket);
        CompletableFuture.runAsync(() -> rabbitTemplate.convertAndSend(exchange, routingKey,
                new UserInfo(passenger.getUsername(), LocalDateTime.now(), Activity.SELL_TICKET)));
        log.info("TICKET WITH ID {} WAS SOLD BY {}", ticketId, passenger.getUsername());

    }

}
