package com.company.innowise.airticketsapp.businessservice.security;

import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PassengerDetailsService implements UserDetailsService {

    private final PassengerRepository passengerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Passenger passengerByUsername = passengerRepository
                .getPassengerByUsernameAndIsActive(username, true)
                .orElseThrow();

        return new PassengerDetails(passengerByUsername.getUsername(),
                passengerByUsername.getPassword(),
                passengerByUsername.getRoles().stream()
                .map((role -> new SimpleGrantedAuthority(role.name())))
                        .toList());
    }

}
