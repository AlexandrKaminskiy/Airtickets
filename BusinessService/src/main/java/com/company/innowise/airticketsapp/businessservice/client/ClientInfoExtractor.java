package com.company.innowise.airticketsapp.businessservice.client;

import com.company.innowise.airticketsapp.businessservice.dto.Activity;
import com.company.innowise.airticketsapp.businessservice.dto.UserInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient
public interface ClientInfoExtractor {

    @GetMapping("localhost:8081/user/{username}")
    List<UserInfo> getUserInfo(@PathVariable String username,
                               @RequestParam Integer page,
                               @RequestParam Integer size);

    @GetMapping("localhost:8081/activity/{activity}")
    List<UserInfo> getActivityInfo(@PathVariable Activity activity,
                                   @RequestParam Integer page,
                                   @RequestParam Integer size);
}
