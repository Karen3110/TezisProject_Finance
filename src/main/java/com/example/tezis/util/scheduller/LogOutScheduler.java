package com.example.tezis.util.scheduller;

import com.example.tezis.service.UserLoginPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LogOutScheduler {

    @Autowired
    private UserLoginPasswordService userLoginService;

    //todo: enable scheduling when autoLogout.
//    @Scheduled(fixedRate = 60000 ) // 1min
    public void logoutIfExpired() {
        userLoginService.logoutIfExpired();
        System.out.println("logout scheduler");
    }
}
