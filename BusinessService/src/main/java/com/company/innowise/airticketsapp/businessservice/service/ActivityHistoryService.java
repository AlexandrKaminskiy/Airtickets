package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.client.ClientInfoExtractor;
import com.company.innowise.airticketsapp.businessservice.dto.Activity;
import com.company.innowise.airticketsapp.businessservice.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityHistoryService {

    private final ClientInfoExtractor clientInfoExtractor;

    public List<UserInfo> getUserActivityInfo(String username, Integer page, Integer size) {
        return clientInfoExtractor.getUserInfo(username, page, size);
    }

    public List<UserInfo> getActivityInfo(Activity activity, Integer page, Integer size) {
        return clientInfoExtractor.getActivityInfo(activity, page, size);
    }

}
