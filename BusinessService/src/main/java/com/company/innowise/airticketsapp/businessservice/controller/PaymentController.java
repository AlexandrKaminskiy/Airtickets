package com.company.innowise.airticketsapp.businessservice.controller;

import com.company.innowise.airticketsapp.businessservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/pay/{ticketId}")
    public String payTicket(@PathVariable Long ticketId) {
        paymentService.purchaseTicket(ticketId);
        return "ticket was paid";
    }

    @PostMapping("/sell/{ticketId}")
    public String sellTicket(@PathVariable Long ticketId) {
        paymentService.sellTicket(ticketId);
        return "ticket was sold";
    }

}
