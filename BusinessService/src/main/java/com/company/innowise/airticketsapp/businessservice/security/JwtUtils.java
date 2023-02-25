package com.company.innowise.airticketsapp.businessservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.company.innowise.airticketsapp.businessservice.model.Passenger;
import com.company.innowise.airticketsapp.businessservice.repository.JwtRepository;
import com.company.innowise.airticketsapp.businessservice.repository.PassengerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value(("${jwt.subject}"))
    private String iss;

    @Value("${jwt.access-exp}")
    private long accessExpired;

    @Value("${jwt.refresh-exp}")
    private long refreshExpired;
    private Algorithm algorithm;

    private JWTVerifier verifier;

    private final PassengerRepository clientRepository;

    private final JwtRepository jwtRepository;

    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC256(secret);
        verifier = JWT.require(algorithm)
                .withIssuer(iss)
                .build();
        System.out.println(iss);
    }

    public String createToken(UserDetails client, boolean isAccess) {
        long expired = isAccess ? accessExpired : refreshExpired;
        String jwtToken = JWT.create()
                .withIssuer(iss)
                .withClaim("username", client.getUsername())
                .withIssuedAt(new Date())
                .withClaim("roles", client.getAuthorities().stream().map((GrantedAuthority::getAuthority)).toList())
                .withExpiresAt(new Date(System.currentTimeMillis() + expired))
                .sign(algorithm);
        System.out.println(jwtToken);
        return jwtToken;
    }

    @Transactional
    public PassengerDetails verifyToken(String token) {
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getClaim("username").asString();
        Passenger passenger = clientRepository.getPassengerByUsername(username).orElseThrow();
        jwtRepository.findByPassengerUsername(passenger.getUsername());
        return new PassengerDetails(passenger.getUsername(), passenger.getPassword(),
                passenger.getRoles().stream()
                        .map((role)->new SimpleGrantedAuthority(role.name()))
                        .toList());
    }
}
