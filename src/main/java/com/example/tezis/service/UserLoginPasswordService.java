package com.example.tezis.service;

import com.example.tezis.dao.model.user.UserDetails;
import com.example.tezis.dao.request.userController.UserLoginDto;
import com.example.tezis.util.exceptions.UserNotFoundException;
import com.example.tezis.util.user.UserRole;

public interface UserLoginPasswordService {
    int saveUser(String login, String password, UserRole role) throws UserNotFoundException;

    void logoutIfExpired();

    int makeActiveUser(UserLoginDto loginData) throws UserNotFoundException;

    void logout(UserDetails userDetails) throws UserNotFoundException;
}
