package com.example.tezis.dao.model.excel.ui;

import com.example.tezis.dao.model.excel.ui.details.ExcelUiContent;
import com.example.tezis.dao.model.excel.ui.details.ExcelUiHeader;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Component
public class ExcelUi {

    private int excelFileId;
    private ExcelUiHeader header;

    private ExcelUiContent content;


    public void setContent(int id, Workbook workbook) {
        excelFileId = id;
        if (workbook == null) {
            header = null;
            content = null;
            return;
        }

        // sheet at:0 is for question :: predefined
        List<List<Row>> excelSheetSectionSplit = getExcelSheetSectionSplit(workbook, 0);
// FIXME: 3/31/23   karen: null pter listi vra
        header = getUiHeader(excelSheetSectionSplit);
        content = getUiContent(excelSheetSectionSplit);

    }

    private ExcelUiHeader getUiHeader(List<List<Row>> sections) {
        ExcelUiHeader headerContent = new ExcelUiHeader();

        List<Row> headerSectionRows = sections.get(0);

        headerContent.setHeader(headerSectionRows.get(0).getCell(0).getStringCellValue());
        headerContent.setDescription(headerSectionRows.get(1).getCell(0).getStringCellValue());
        return headerContent;
    }


    private ExcelUiContent getUiContent(List<List<Row>> sections) {
        ExcelUiContent content = new ExcelUiContent();
        for (int i = 1; i < sections.size(); i++) {
            content.addSection(sections.get(i));
        }

        return content;
    }


    private List<List<Row>> getExcelSheetSectionSplit(Workbook workbook, int sheetAt) {
        if (workbook == null || sheetAt < 0 || sheetAt > workbook.getNumberOfSheets()) {
            return null;
        }
        Sheet sheet = workbook.getSheetAt(sheetAt);
        return getSections(sheet);

    }

    private List<List<Row>> getSections(Sheet sheet) {
        if (sheet == null) {
            return null;
        }

        List<List<Row>> data = new LinkedList<>();

        List<Row> sectionRows = new LinkedList<>();
        for (Row row : sheet) {
            if (StringUtils.isNotEmpty(row.getCell(0).getStringCellValue())) {
                sectionRows.add(row);
            } else {
                // skip empty rows and create new section
                data.add(sectionRows);
                sectionRows = new LinkedList<>();
            }
        }
        return data;
    }

}
