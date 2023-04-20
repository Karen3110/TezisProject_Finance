package com.example.tezis.dao.model.excel.ui.details;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
@RequiredArgsConstructor
public class ExcelUiSection {
    private String header;
    private String description;
    private List<ExcelUiItem> data = new LinkedList<>();

    public ExcelUiSection(List<Row> sectionRows) {
        header = sectionRows.get(0).getCell(0).getStringCellValue();
        sectionRows.remove(0);
        description = sectionRows.get(0).getCell(0).getStringCellValue();
        sectionRows.remove(0);

        for (Row sectionRow : sectionRows) {
            ExcelUiItem sectionItem = new ExcelUiItem();

            //name
            sectionItem.setName(sectionRow.getCell(0).getStringCellValue());

            //value
            Cell valueCell = sectionRow.getCell(1);
            sectionItem.setType(valueCell.getCellType());
            sectionItem.setValue(getCellValue(valueCell));

            //description
            sectionItem.setDescriptor(sectionRow.getCell(2).getStringCellValue());
            data.add(sectionItem);
        }

    }


    private Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        if (Objects.requireNonNull(cellType) == org.apache.poi.ss.usermodel.CellType.NUMERIC) {
            double numberValue = cell.getNumericCellValue();
            // BigDecimal is used to avoid double value is counted use Scientific counting method.
            return BigDecimal.valueOf(numberValue);
        } else if (cellType == org.apache.poi.ss.usermodel.CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cellType == org.apache.poi.ss.usermodel.CellType.BOOLEAN) {
            return cell.getBooleanCellValue();
        } else if (cellType == org.apache.poi.ss.usermodel.CellType.BLANK) {
            return "";
        } else {
            return null;
        }


    }


}
