package com.example.tezis.controller;

import com.example.tezis.dao.model.user.UserDetails;
import com.example.tezis.dao.request.userController.UserLoginDto;
import com.example.tezis.dao.request.userController.UserRegisterDto;
import com.example.tezis.dao.response.userController.RegistrationSuccess;
import com.example.tezis.service.UserDetailsService;
import com.example.tezis.util.exceptions.UserNotFoundException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")

//todo: karen: define how to get user data when need to do user login/logout/register processes
public class LoginController {


    @Autowired
    private Gson GSON;

    @Autowired
    private UserDetailsService userService;


    @PostMapping(value = "/register", produces = "application/json; charset=utf-8")
    ResponseEntity<String> registerUser(@RequestParam boolean isAdmin, @RequestBody UserRegisterDto userData) {

        try {
            RegistrationSuccess registrationSuccess = userService.registerUser(userData, isAdmin);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(GSON.toJson(registrationSuccess));

        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(GSON.toJson(e.getMessage()));

        }
    }

    @PostMapping(value = "/login", produces = "application/json; charset=utf-8")
    ResponseEntity<String> loginUser(@RequestBody UserLoginDto loginData) {
        try {
            UserDetails loggedUser = userService.login(loginData);
            loggedUser.setUserLoginId(0);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(GSON.toJson(loggedUser));

        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(GSON.toJson(e.getMessage()));

        }
    }


    @PostMapping(value = "/logout", produces = "application/json; charset=utf-8")
    ResponseEntity<String> logOutUser(@RequestParam int id) {
        try {
            userService.logout(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(GSON.toJson(null));

        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(GSON.toJson(e.getMessage()));

        }
    }


}
