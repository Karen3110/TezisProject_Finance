package com.example.tezis.dao.response.fileController;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class FileDescription {

    @SerializedName(value = "fileId")
    private int id;

    private String fileName;

    public FileDescription(int id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    //todo: karen: implement more fields logic, to get data for UI for user.
}
