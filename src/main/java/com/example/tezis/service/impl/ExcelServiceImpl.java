package com.example.tezis.service.impl;

import com.example.tezis.dao.model.excel.ExcelFile;
import com.example.tezis.dao.model.excel.ui.ExcelUi;
import com.example.tezis.repository.FileRepository;
import com.example.tezis.service.ExcelService;
import com.example.tezis.util.exceptions.SheetNotFoundException;
import com.example.tezis.util.parser.ExcelFileParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class ExcelServiceImpl implements ExcelService {

    private final FileRepository fileRepository;

    public ExcelServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override  //todo: karen refactor to fileId
    public ExcelUi getExcelUiModel(String fileName) throws FileNotFoundException {
        if (StringUtils.isEmpty(fileName)) {
            return null;
        }
        ExcelFile excelFile = fileRepository.findByFileName(fileName);
        if (excelFile == null) {
            throw new FileNotFoundException("Model with name " + fileName + " not found.");
        }
        return ExcelFileParser.parseToUiModel(excelFile);

    }


}
