package com.example.tezis.dao.response.userController;

public class FileAssignFiled {
    private final String message = "Registration failed.";

    private final String reason;

    public FileAssignFiled(String reason) {
        this.reason = reason;
    }
}
