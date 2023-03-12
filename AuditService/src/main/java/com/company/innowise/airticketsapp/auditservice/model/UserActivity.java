package com.company.innowise.airticketsapp.auditservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class UserActivity {

    private String username;
    private LocalDateTime activityTime;
    private Activity activity;

}