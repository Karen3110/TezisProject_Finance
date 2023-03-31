package com.example.tezis.service;

import com.example.tezis.dao.model.excel.ExcelModel;
import com.example.tezis.dao.model.excel.ExcelSheet;
import com.example.tezis.dao.model.excel.ui.ExcelUi;
import com.example.tezis.util.exceptions.SheetNotFoundException;

import java.io.FileNotFoundException;

public interface ExcelService {

    ExcelModel getModelByName(String excelName) throws FileNotFoundException;

    ExcelSheet getModelSheet(String excelName, String sheetName) throws FileNotFoundException, SheetNotFoundException;

    ExcelUi getExcelUiModel(String fileName) throws FileNotFoundException;
}
