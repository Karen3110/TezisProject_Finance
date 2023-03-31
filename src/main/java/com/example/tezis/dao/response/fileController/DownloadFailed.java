package com.example.tezis.dao.response.fileController;

public class DownloadFailed {
    private String message;

    public DownloadFailed(String fileName) {
        this.message = "File with name: " + fileName + " not found to download, check server logs for more information.";
    }

    public DownloadFailed(String fileName, String reason) {
        this.message = "File with name: " + fileName + " not found to download, " + reason;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
