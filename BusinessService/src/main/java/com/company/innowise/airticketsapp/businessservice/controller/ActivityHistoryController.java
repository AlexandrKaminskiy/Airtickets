package com.company.innowise.airticketsapp.businessservice.controller;

import com.company.innowise.airticketsapp.businessservice.dto.Activity;
import com.company.innowise.airticketsapp.businessservice.dto.UserInfo;
import com.company.innowise.airticketsapp.businessservice.service.ActivityHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
public class ActivityHistoryController {

    private final ActivityHistoryService activityHistoryService;

    @GetMapping("/{username}")
    public List<UserInfo> getUserActions(@PathVariable String username,
                                         @RequestParam(defaultValue = "0") Integer page,
                                         @RequestParam(defaultValue = "10") Integer size) {
        return activityHistoryService.getUserActivityInfo(username, page, size);
    }

    @GetMapping("/{activity}")
    public List<UserInfo> getUserActions(@PathVariable Activity activity,
                                         @RequestParam(defaultValue = "0") Integer page,
                                         @RequestParam(defaultValue = "10") Integer size) {
        return activityHistoryService.getActivityInfo(activity, page, size);
    }

}
