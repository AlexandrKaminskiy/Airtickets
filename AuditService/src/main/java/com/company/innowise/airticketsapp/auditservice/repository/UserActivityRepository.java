package com.company.innowise.airticketsapp.auditservice.repository;

import com.company.innowise.airticketsapp.auditservice.model.UserActivity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserActivityRepository extends MongoRepository<UserActivity, Long> {

    List<UserActivity> findByUsername(String username);

}
