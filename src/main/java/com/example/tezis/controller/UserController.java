package com.example.tezis.controller;

import com.example.tezis.dao.response.userController.FileAssignFiled;
import com.example.tezis.service.UserDetailsService;
import com.example.tezis.util.exceptions.UserNotFoundException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private Gson GSON;

    @PostMapping("assign/{userId}/{fileId}")
    public ResponseEntity<String> assignFileToUser(@PathVariable("userId") int userId, @PathVariable("fileId") int fileId) {
        try {
            userDetailsService.assignFileToUser(userId, fileId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (UserNotFoundException | FileNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(GSON.toJson(new FileAssignFiled(e.getMessage())));
        }

    }

    //todo 1: Kostan: implement release method for user and file
    //todo 2: Kostan: implement get method to see all assigned files.


}
