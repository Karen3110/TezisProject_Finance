package com.example.tezis.dao.model.excel;

import com.example.tezis.config.AttributeEncryptor;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class ExcelFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    @Convert(converter = AttributeEncryptor.class)
    private byte[] data;

    private String fileName;

    private boolean isUserAssigned = false;

    public ExcelFile(String fileName, byte[] bytes) {
        this.fileName = fileName;
        this.data = bytes;
    }

    public ExcelFile copy() {
        ExcelFile clone = new ExcelFile();
        clone.setData(data.clone());
        clone.setFileName(fileName);
        clone.setId(0);
        clone.setUserAssigned(isUserAssigned);
        return clone;
    }
}
