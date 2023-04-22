package com.example.tezis.controller;

import com.example.tezis.dao.response.fileController.FileDescription;
import com.example.tezis.dao.response.userController.FileAssignFiled;
import com.example.tezis.dao.response.userController.FileReleaseFiled;
import com.example.tezis.service.UserDetailsService;
import com.example.tezis.util.exceptions.UserNotFoundException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

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

    @PutMapping("release/{userId}/{fileId}")
    public ResponseEntity<String> releaseFileFromUser(@PathVariable("userId") int userId, @PathVariable("fileId") int fileId) {
        try {
            userDetailsService.releaseFileFromUser(userId, fileId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (UserNotFoundException | FileNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(GSON.toJson(new FileReleaseFiled(e.getMessage())));
        }

    }

    @GetMapping(value = "/get/{userId}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FileDescription>> getAllAssignedFiles(@PathVariable("userId") int userId) throws UserNotFoundException {
        List<FileDescription> assignedFiles = userDetailsService.getAllAssigned(userId);
        return ResponseEntity.ok(assignedFiles);
    }

}
