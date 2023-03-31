package com.example.tezis.dao.response.excelController;

public class ModelFoundFailed {

    private final String message;

    public ModelFoundFailed(Exception exception) {
        this.message = "Model can not be found because of: " + exception.getMessage();
    }
}
