package com.company.innowise.airticketsapp.auditservice;

import com.company.innowise.airticketsapp.auditservice.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AuditServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(AuditServiceApplication.class, args);
        UserService userService = (UserService) run.getBean("userService");
        userService.getUserActivity("babuba", 0, 10);
    }

}
