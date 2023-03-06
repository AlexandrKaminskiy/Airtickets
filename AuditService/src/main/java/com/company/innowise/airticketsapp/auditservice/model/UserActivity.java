package com.company.innowise.airticketsapp.auditservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@AllArgsConstructor
@Getter
@Setter
public class UserActivity {

    @Id
    private Long id;

    private String username;

    private LocalDate activityTime;

    private Activity activity;

}