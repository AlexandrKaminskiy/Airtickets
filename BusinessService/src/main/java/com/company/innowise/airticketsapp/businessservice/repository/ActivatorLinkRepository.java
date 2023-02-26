package com.company.innowise.airticketsapp.businessservice.repository;

import com.company.innowise.airticketsapp.businessservice.model.ActivatorLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivatorLinkRepository extends JpaRepository<ActivatorLink, Long> {

    Optional<ActivatorLink> findByUuid(String uuid);

}
