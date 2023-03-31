package com.example.tezis.dao.model.excel;

import com.example.tezis.util.parser.CellType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class ExcelCell {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private CellType cellType;

    private String value;

    private Integer rowIndex;

    private Integer colIndex;

    private String cellAddress;

    public ExcelCell(Cell cell) {
        cellType = determineCellType(cell);
        rowIndex = cell.getRowIndex();
        colIndex = cell.getColumnIndex();
        cellAddress = cell.getAddress().formatAsString();

        setCellValue(cell);
    }

    private void setCellValue(Cell cell) {
        String stringCellValue;
        if (Objects.requireNonNull(cellType) == CellType.NUMERIC) {
            double numberValue = cell.getNumericCellValue();
            // BigDecimal is used to avoid double value is counted use Scientific counting method.
            stringCellValue = BigDecimal.valueOf(numberValue).toPlainString();
        } else if (cellType == CellType.STRING) {
            stringCellValue = cell.getStringCellValue();
        } else if (cellType == CellType.BOOLEAN) {
            boolean numberValue = cell.getBooleanCellValue();
            stringCellValue = String.valueOf(numberValue);
        } else if (cellType == CellType.BLANK) {
            stringCellValue = "";
        } else if (cellType == CellType.COMBO_BOX) {
            //todo: karen need to implement of determaining of combobox
            stringCellValue = cell.getCellFormula();
        } else {
            stringCellValue = null;
        }
        value = stringCellValue;
    }

    private CellType determineCellType(Cell cell) {
        org.apache.poi.ss.usermodel.CellType type = cell.getCellType();
        if (Objects.requireNonNull(type) == org.apache.poi.ss.usermodel.CellType.NUMERIC) {
            return CellType.NUMERIC;
        } else if (type == org.apache.poi.ss.usermodel.CellType.STRING) {
            return CellType.STRING;
        } else if (type == org.apache.poi.ss.usermodel.CellType.BOOLEAN) {
            return CellType.BOOLEAN;
        } else if (type == org.apache.poi.ss.usermodel.CellType.BLANK) {
            return CellType.BLANK;
        } else if (type == org.apache.poi.ss.usermodel.CellType.FORMULA &&
                cell.getCachedFormulaResultType() == org.apache.poi.ss.usermodel.CellType.STRING) {
            //todo: karen need to implement of determaining of combobox
            String formula = cell.getCellFormula();
            if (cell.getAddress().formatAsString().equals("B6")) {
                return CellType.COMBO_BOX;
            }

        }
        return CellType._NONE;
    }
}
