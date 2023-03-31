package com.example.tezis.dao.response.fileController;

public class UploadSuccess {
    private Integer uploadId;
    private String message = "Uploaded successfully.";

    public UploadSuccess(Integer uploadedNumber) {
        this.uploadId = uploadedNumber;
    }

    public UploadSuccess(Integer uploadedNumber, String fileName) {
        this.uploadId = uploadedNumber;
        this.message = "File with name: " + fileName + " uploaded successfully.";
    }

    public Integer getUploadId() {
        return uploadId;
    }

    public void setUploadId(Integer uploadId) {
        this.uploadId = uploadId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
