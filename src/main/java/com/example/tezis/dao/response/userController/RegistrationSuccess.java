package com.example.tezis.dao.response.userController;

import lombok.Data;

@Data
public class RegistrationSuccess {
    private String message = "Registration successfully completed.";

    private int userId;

    private String token;


    public RegistrationSuccess(int userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public RegistrationSuccess(int userId) {
        this.userId = userId;
        this.token = "SOME_TOKEN";
    }
}
