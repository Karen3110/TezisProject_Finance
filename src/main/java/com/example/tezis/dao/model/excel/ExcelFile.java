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


    public ExcelFile(String fileName, byte[] bytes) {
        this.fileName = fileName;
        this.data = bytes;
    }
}
