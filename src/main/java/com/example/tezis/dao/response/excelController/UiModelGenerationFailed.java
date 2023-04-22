package com.example.tezis.dao.response.excelController;

public class UiModelGenerationFailed {

    private final String message;

    public UiModelGenerationFailed(Exception exception) {
        this.message = "UI model can not be generated because of: " + exception.getMessage();
    }
}
