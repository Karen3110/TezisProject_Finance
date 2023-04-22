package com.example.tezis.dao.response.userController;

public class FileReleaseFiled {
    private final String message = "Release file from user failed.";

    private final String reason;

    public FileReleaseFiled(String reason) {
        this.reason = reason;
    }
}
