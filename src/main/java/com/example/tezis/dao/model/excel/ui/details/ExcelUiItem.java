package com.example.tezis.dao.model.excel.ui.details;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.CellType;

@Data
@RequiredArgsConstructor
public class ExcelUiItem {
    private String name;
    private Object value;
    private CellType type;
    private String descriptor;


    // todo: karen: add validations too, for value;
}
