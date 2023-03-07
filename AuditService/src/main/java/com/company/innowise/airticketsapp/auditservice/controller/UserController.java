package com.company.innowise.airticketsapp.auditservice.controller;

import com.company.innowise.airticketsapp.auditservice.model.Activity;
import com.company.innowise.airticketsapp.auditservice.service.UserService;
import com.company.innowise.airticketsapp.auditservice.model.UserActivity;
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
                                      @RequestParam Integer page,
                                      @RequestParam Integer size) {
        return userService.getUserActivity(username, page, size);
    }

    @GetMapping("/activity/{activity}")
    public List<UserActivity> getActivity(@PathVariable Activity activity,
                                      @RequestParam Integer page,
                                      @RequestParam Integer size) {
        return userService.getActivityInfo(activity, page, size);
    }
}
