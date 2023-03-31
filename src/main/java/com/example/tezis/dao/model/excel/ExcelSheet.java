package com.example.tezis.dao.model.excel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;


@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class ExcelSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    private String name;

    @OneToMany
    private List<ExcelRow> rows = new LinkedList<>();

    public ExcelSheet(Sheet sheet) {
        name = sheet.getSheetName();

        for (Row row : sheet) {
            ExcelRow excelRow = new ExcelRow(row);
            rows.add(excelRow);
        }

    }
}
