package com.example.tezis.dao.model.excel;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;


@Getter
@Setter
@Entity
public class ExcelModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    private String fileName;

    @OneToMany
    private List<ExcelSheet> sheets = new LinkedList<>();

    public ExcelModel() {
    }

    public ExcelModel(int id, String fileName, Workbook workbook) {
        this.id = id;
        this.fileName = fileName;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheetAt = workbook.getSheetAt(i);
            ExcelSheet excelSheet = new ExcelSheet(sheetAt);
            sheets.add(excelSheet);

        }
    }

    public ExcelSheet getSheet(String sheetName) {
        if (StringUtils.isEmpty(sheetName)) {
            return null;
        }
        return sheets.stream().filter(item -> item.getName().equals(sheetName)).findFirst().orElse(null);
    }


}
