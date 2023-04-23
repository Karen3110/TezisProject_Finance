package com.example.tezis.dao.response.userController;

public class FileReleaseSuccess {
    private final String message = "Release file from user successed";

    private final String reason;

    public FileReleaseSuccess(String reason) {
        this.reason = reason;
    }

    public FileReleaseSuccess() {
        this.reason=null;
    }
}
