package com.company.innowise.airticketsapp.businessservice.controller;

import com.company.innowise.airticketsapp.businessservice.dto.PassengerCredentials;
import com.company.innowise.airticketsapp.businessservice.dto.Token;
import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.model.Role;
import com.company.innowise.airticketsapp.businessservice.repository.PassengerRepository;
import com.company.innowise.airticketsapp.businessservice.service.PassengerService;
import com.company.innowise.airticketsapp.businessservice.service.PaymentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private PassengerRepository repository;

    @MockBean
    private PaymentService paymentService;
    private Passenger passenger;

    @BeforeEach
    @Transactional
    public void setup() {

        passenger = new Passenger();
        passenger.setRoles(new HashSet<>(Set.of(Role.ROLE_PASSENGER, Role.ROLE_MANAGER, Role.ROLE_ADMIN)));
        passenger.setUsername("test_user");
        passenger.setPassword(passwordEncoder.encode("test_password"));
        passenger.setIsActive(true);
        repository.save(passenger);

    }

    @Test
    void payTicket() throws Exception {

        Mockito.doNothing()
                .when(paymentService)
                .purchaseTicket(ArgumentMatchers.anyLong(), ArgumentMatchers.any(Principal.class));
        Token token = passengerService.signIn(new PassengerCredentials("test_user", "test_password"));
        mockMvc.perform(post("/api/payment/pay/0")
                        .header(AUTHORIZATION, "Bearer " + token.getAccessToken()))
                .andExpect(status().isOk());

    }

    @AfterEach
    @Transactional
    public void removePassenger() {
        repository.delete(passenger);
    }

}