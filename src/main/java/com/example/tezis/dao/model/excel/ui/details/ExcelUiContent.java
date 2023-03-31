package com.example.tezis.dao.model.excel.ui.details;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;

import java.util.LinkedList;
import java.util.List;


@Data
@RequiredArgsConstructor
public class ExcelUiContent {

    private List<ExcelUiSection> sections;


    public void addSection(List<Row> sectionRows) {

        if (sections == null) {
            sections = new LinkedList<>();
        }
        ExcelUiSection section = new ExcelUiSection(sectionRows);
        sections.add(section);

    }
}
