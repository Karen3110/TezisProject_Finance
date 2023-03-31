package com.example.tezis.service;

import com.example.tezis.dao.model.user.UserDetails;
import com.example.tezis.dao.request.userController.UserLoginDto;
import com.example.tezis.dao.request.userController.UserRegisterDto;
import com.example.tezis.dao.response.userController.RegistrationSuccess;
import com.example.tezis.util.exceptions.UserNotFoundException;

import java.io.FileNotFoundException;


public interface UserDetailsService {
    RegistrationSuccess registerUser(UserRegisterDto userData, boolean isAdmin) throws UserNotFoundException;

    UserDetails login(UserLoginDto loginData) throws UserNotFoundException;

    void logout(int id) throws UserNotFoundException;

    void assignFileToUser(int userId, int fileId) throws UserNotFoundException, FileNotFoundException;
}
