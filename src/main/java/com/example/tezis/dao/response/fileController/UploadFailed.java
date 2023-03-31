package com.example.tezis.dao.response.fileController;

public class UploadFailed {
    private String message;

    public UploadFailed(String fileName) {
        this.message = "File with name: " + fileName + " failed to upload, check server logs for more information.";
    }

    public UploadFailed(String fileName, String reason) {
        this.message = "File with name: " + fileName + " failed to upload, " + reason;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
