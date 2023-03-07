package com.company.innowise.airticketsapp.auditservice.controller;

import com.company.innowise.airticketsapp.auditservice.model.Activity;
import com.company.innowise.airticketsapp.auditservice.model.UserActivity;
import com.company.innowise.airticketsapp.auditservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/user/{username}")
    public List<UserActivity> getUser(@PathVariable String username,
                                      @RequestParam(defaultValue = "0") Integer page,
                                      @RequestParam(defaultValue = "10") Integer size) {
        return userService.getUserActivity(username, page, size);
    }

    @GetMapping("/activity/{activity}")
    public List<UserActivity> getActivity(@PathVariable Activity activity,
                                      @RequestParam int page,
                                      @RequestParam int size) {
        return userService.getActivityInfo(activity, page, size);
    }
}
