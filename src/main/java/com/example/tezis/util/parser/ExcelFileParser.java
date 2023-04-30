package com.example.tezis.util.parser;

import com.example.tezis.dao.model.excel.ExcelFile;
import com.example.tezis.dao.model.excel.ui.ExcelUi;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExcelFileParser {

    public static ExcelUi parseToUiModel(ExcelFile excelFile) {
        Workbook workbook = getWorkbook(excelFile);
        if (workbook == null) {
            return null;
        }

        ExcelUi excelUi = new ExcelUi();
        excelUi.setContent(excelFile.getId(), workbook);
        return excelUi;


    }

    private static Workbook getWorkbook(ExcelFile excelFile) {
        byte[] data = excelFile.getData();
        InputStream inputStream = new ByteArrayInputStream(data);
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return workbook;
    }
}
