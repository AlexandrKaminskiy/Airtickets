package com.company.innowise.airticketsapp.auditservice.listener;

import com.company.innowise.airticketsapp.auditservice.model.UserActivity;
import com.company.innowise.airticketsapp.auditservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditListener {

    private final UserService userService;

    @RabbitListener(queues = "${rabbit.queue}")
    public void audit(UserActivity userActivity) {
        userService.addActivity(userActivity);
    }

}