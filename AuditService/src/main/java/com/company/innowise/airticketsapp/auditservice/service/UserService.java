package com.company.innowise.airticketsapp.auditservice.service;

import com.company.innowise.airticketsapp.auditservice.model.Activity;
import com.company.innowise.airticketsapp.auditservice.model.UserActivity;
import com.company.innowise.airticketsapp.auditservice.repository.UserActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserActivityRepository userActivityRepository;

    public List<UserActivity> getUserActivity(String username, Integer page, Integer size) {
        return userActivityRepository.findByUsername(username, Pageable.ofSize(size).withPage(page)).toList();
    }

    public List<UserActivity> getActivityInfo(Activity activity, Integer page, Integer size) {
        return userActivityRepository.findByActivity(activity, Pageable.ofSize(size).withPage(page)).toList();
    }
}