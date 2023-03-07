package com.company.innowise.airticketsapp.businessservice.repository;

import com.company.innowise.airticketsapp.businessservice.model.JwtHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface JwtRepository extends JpaRepository<JwtHolder, Long> {

    Optional<JwtHolder> findByPassengerUsername(String username);
    Optional<JwtHolder> findByAccessToken(String token);
    Optional<JwtHolder> findByRefreshToken(String token);

}
