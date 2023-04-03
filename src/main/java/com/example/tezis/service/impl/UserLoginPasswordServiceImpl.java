package com.example.tezis.service.impl;

import com.example.tezis.dao.model.user.UserDetails;
import com.example.tezis.dao.model.user.UserLoginPassword;
import com.example.tezis.dao.request.userController.UserLoginDto;
import com.example.tezis.repository.UserLoginPasswordRepository;
import com.example.tezis.service.UserLoginPasswordService;
import com.example.tezis.util.exceptions.UserNotFoundException;
import com.example.tezis.util.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional(readOnly = true)
public class UserLoginPasswordServiceImpl implements UserLoginPasswordService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserLoginPasswordRepository loginPasswordRepository;

    @Override
    @Transactional(readOnly = false)
    public int saveUser(String login, String password, UserRole role) throws UserNotFoundException {

        if (!existsUserWithUserName(login)) {
            throw new UserNotFoundException("Unable to create user with provided username, duplicated data.");
        }

        UserLoginPassword save = loginPasswordRepository.save(new UserLoginPassword(login, passwordEncoder.encode(password), role));
        return save.getId();
    }

    @Override
    @Transactional(readOnly = false)
    public void logoutIfExpired() {
        loginPasswordRepository.findAll().forEach(item -> {
            long validUntil = item.getValidUntil();
            long validationPeriod = item.getRole().getValidationPeriod();

            if (item.isEnabled()) {
                if (new Date().after(new Date(validUntil))) {
                    item.setValidUntil(new Date().getTime() + validationPeriod);
                }
            } else {
                item.setValidUntil(0);
            }
        });
    }

    @Override
    @Transactional(readOnly = false)
    public int makeActiveUser(UserLoginDto loginData) throws UserNotFoundException {
        if (existsUserWithUserName(loginData.getLogin())) {
            throw new UserNotFoundException("User with Login not found.");
        }
        UserLoginPassword userToLogin = loginPasswordRepository.getByUsername(loginData.getLogin());
        userToLogin.setEnabled(true);
        userToLogin.refreshValidity();
        return userToLogin.getId();
    }

    @Override
    @Transactional(readOnly = false)
    public void logout(UserDetails userDetails) throws UserNotFoundException {
        UserLoginPassword userLoginPassword = loginPasswordRepository.findById(userDetails.getUserLoginId()).orElseThrow(() -> new UserNotFoundException("User not found."));
        userLoginPassword.setEnabled(false);

    }

    private boolean existsUserWithUserName(String userName) {
        UserLoginPassword byUsername = loginPasswordRepository.getByUsername(userName);
        return byUsername == null;
    }
}
