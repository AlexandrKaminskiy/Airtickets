package com.company.innowise.airticketsapp.auditservice;

import com.company.innowise.airticketsapp.auditservice.model.Activity;
import com.company.innowise.airticketsapp.auditservice.model.UserActivity;
import com.company.innowise.airticketsapp.auditservice.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class AuditServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuditServiceApplication.class, args);
    }

}
