package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.client.ClientInfoExtractor;
import com.company.innowise.airticketsapp.businessservice.dto.Activity;
import com.company.innowise.airticketsapp.businessservice.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityHistoryService {

    private final ClientInfoExtractor clientInfoExtractor;

    public List<UserInfo> getUserActivityInfo(String username, Pageable pageable) {
        return clientInfoExtractor.getUserInfo(username, pageable);
    }

    public List<UserInfo> getActivityInfo(Activity activity, Pageable pageable) {
        return clientInfoExtractor.getActivityInfo(activity, pageable);
    }

}
