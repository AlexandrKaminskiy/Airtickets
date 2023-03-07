package com.company.innowise.airticketsapp.businessservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private String username;
    private LocalDateTime activityTime;
    private Activity activity;

}