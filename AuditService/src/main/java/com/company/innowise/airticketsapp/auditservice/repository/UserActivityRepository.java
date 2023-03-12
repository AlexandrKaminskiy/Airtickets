package com.company.innowise.airticketsapp.auditservice.repository;

import com.company.innowise.airticketsapp.auditservice.model.Activity;
import com.company.innowise.airticketsapp.auditservice.model.UserActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivityRepository extends MongoRepository<UserActivity, Long> {

    Page<UserActivity> findByUsernameOrderByActivityTimeDesc(String username, Pageable pageable);
    Page<UserActivity> findByActivityOrderByActivityTimeDesc(Activity activity, Pageable pageable);

}
