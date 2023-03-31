package com.example.tezis.dao.response.userController;

public class RegistrationFailed {
    private final String message = "Registration failed.";

    private final String reason;

    public RegistrationFailed(String reason) {
        this.reason = reason;
    }
}
