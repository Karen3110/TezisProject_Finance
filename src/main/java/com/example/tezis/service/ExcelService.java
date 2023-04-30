package com.example.tezis.service;

import com.example.tezis.dao.model.excel.ui.ExcelUi;
import com.example.tezis.util.exceptions.SheetNotFoundException;

import java.io.FileNotFoundException;

public interface ExcelService {

    ExcelUi getExcelUiModel(String fileName) throws FileNotFoundException;
}
