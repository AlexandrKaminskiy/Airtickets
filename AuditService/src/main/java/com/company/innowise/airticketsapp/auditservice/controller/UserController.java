package com.company.innowise.airticketsapp.auditservice.controller;

import com.company.innowise.airticketsapp.auditservice.model.Activity;
import com.company.innowise.airticketsapp.auditservice.model.UserActivity;
import com.company.innowise.airticketsapp.auditservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/user/{username}")
    public List<UserActivity> getUser(@PathVariable String username,
                                      Pageable pageable) {
        return userService.getUserActivity(username, pageable);
    }

    @GetMapping("/activity/{activity}")
    public List<UserActivity> getActivity(@PathVariable Activity activity,
                                          Pageable pageable) {
        return userService.getActivityInfo(activity, pageable);
    }
}
