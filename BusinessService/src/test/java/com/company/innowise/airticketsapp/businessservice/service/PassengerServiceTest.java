package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.dto.NewPassengerDto;
import com.company.innowise.airticketsapp.businessservice.mail.MailService;
import com.company.innowise.airticketsapp.businessservice.model.ActivatorLink;
import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.model.Role;
import com.company.innowise.airticketsapp.businessservice.repository.ActivatorLinkRepository;
import com.company.innowise.airticketsapp.businessservice.repository.PassengerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@SpringBootTest
class PassengerServiceTest {

    @Autowired
    private PassengerService passengerService;

    @MockBean
    private PassengerRepository passengerRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private MailService mailService;

    @MockBean
    private ActivatorLinkRepository activatorLinkRepository;

    @Test
    void signUp() {
        Mockito.doReturn(new ArrayList<>())
                .when(passengerRepository)
                .getPassengerByUsernameOrEmail(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString()
                );
        Mockito.doReturn(new ActivatorLink())
                .when(activatorLinkRepository)
                .save(
                        ArgumentMatchers.any(ActivatorLink.class)
                );
        Mockito.doNothing()
                .when(mailService)
                .sendMail(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString()
                );
        Mockito.doReturn("secretPass")
                .when(passwordEncoder)
                .encode(
                        ArgumentMatchers.anyString()
                );
        NewPassengerDto newPassengerDto =
                new NewPassengerDto("", "", "", "", "", "", LocalDate.now());
        passengerService.signUp(newPassengerDto);
        Mockito.verify(passengerRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Passenger.class));
    }

    @Test
    void changeRole() {
        Mockito.doReturn(Optional.of(new Passenger()))
                .when(passengerRepository)
                .getPassengerByUsername(
                        ArgumentMatchers.anyString()
                );
        passengerService.changeRole("", Collections.singleton(Role.ROLE_ADMIN));
        Mockito.verify(passengerRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Passenger.class));
    }

}