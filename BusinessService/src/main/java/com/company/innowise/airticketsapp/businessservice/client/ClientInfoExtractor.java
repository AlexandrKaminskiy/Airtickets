package com.company.innowise.airticketsapp.businessservice.client;

import com.company.innowise.airticketsapp.businessservice.dto.Activity;
import com.company.innowise.airticketsapp.businessservice.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "user-audit", url = "${auditHost}")
public interface ClientInfoExtractor {

    @GetMapping("/user/{username}")
    List<UserInfo> getUserInfo(@PathVariable String username,
                               Pageable pageable);

    @GetMapping("/activity/{activity}")
    List<UserInfo> getActivityInfo(@PathVariable Activity activity,
                                   Pageable pageable);
}
