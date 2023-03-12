package com.company.innowise.airticketsapp.businessservice.controller;

import com.company.innowise.airticketsapp.businessservice.dto.Activity;
import com.company.innowise.airticketsapp.businessservice.dto.UserInfo;
import com.company.innowise.airticketsapp.businessservice.service.ActivityHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
public class ActivityHistoryController {

    private final ActivityHistoryService activityHistoryService;

    @GetMapping("/user/{username}")
    public List<UserInfo> getUserActions(@PathVariable String username,
                                         @PageableDefault Pageable pageable) {
        return activityHistoryService.getUserActivityInfo(username, pageable);
    }

    @GetMapping("/activity/{activity}")
    public List<UserInfo> getUserActions(@PathVariable Activity activity,
                                         @PageableDefault Pageable pageable) {
        return activityHistoryService.getActivityInfo(activity, pageable);
    }

}
