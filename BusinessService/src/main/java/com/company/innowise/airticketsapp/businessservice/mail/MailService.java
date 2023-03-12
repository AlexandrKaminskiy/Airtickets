package com.company.innowise.airticketsapp.businessservice.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    @Value("${hostActivate}")
    private String hostname;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendMail(String email, String uuid) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(email);
        message.setSubject("Airtickets application");
        message.setText(hostname + uuid);
        javaMailSender.send(message);
        log.info("MAIL WAS SENT TO {}", email);

    }

}
