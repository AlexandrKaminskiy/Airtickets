package com.company.innowise.airticketsapp.auditservice.listener;

import com.company.innowise.airticketsapp.auditservice.model.UserActivity;
import com.company.innowise.airticketsapp.auditservice.repository.UserActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditListener {


    private UserActivityRepository userActivityRepository;

    @RabbitListener(bindings = { @QueueBinding(exchange = @Exchange("${rabbit.exchange.authenticate}"),
            key = "${rabbit.key.authenticate}", value = @Queue("${rabbit.queue}")) } )
    public void audit(UserActivity userActivity) {
        userActivityRepository.insert(userActivity);
    }

}
