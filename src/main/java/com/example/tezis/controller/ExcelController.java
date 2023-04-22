package com.example.tezis.controller;

import com.example.tezis.dao.model.excel.ExcelModel;
import com.example.tezis.dao.model.excel.ExcelSheet;
import com.example.tezis.dao.model.excel.ui.ExcelUi;
import com.example.tezis.dao.response.excelController.ModelFoundFailed;
import com.example.tezis.dao.response.excelController.UiModelGenerationFailed;
import com.example.tezis.service.ExcelService;
import com.example.tezis.util.exceptions.SheetNotFoundException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/excel/model")
public class ExcelController {

    private final ExcelService excelService;
    @Autowired
    private Gson GSON;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("/{fileName}")
    ResponseEntity<String> getExcelModel(@PathVariable String fileName) {
        try {
            ExcelModel modelByName = excelService.getModelByName(fileName);
            return ResponseEntity.
                    status(HttpStatus.OK).
                    contentType(MediaType.APPLICATION_JSON).
                    body(GSON.toJson(modelByName));

        } catch (FileNotFoundException ex) {
            return ResponseEntity.
                    status(HttpStatus.FORBIDDEN).
                    contentType(MediaType.APPLICATION_JSON).
                    body(GSON.toJson(new ModelFoundFailed(ex)));
        }
    }

    @GetMapping("/{fileName}/{sheetName}")
    ResponseEntity<String> getExcelModel(@PathVariable String fileName, @PathVariable String sheetName) {
        try {
            ExcelSheet sheet = excelService.getModelSheet(fileName, sheetName);
            return ResponseEntity.
                    status(HttpStatus.OK).
                    contentType(MediaType.APPLICATION_JSON).
                    body(GSON.toJson(ResponseEntity.ok(sheet)));

        } catch (FileNotFoundException | SheetNotFoundException ex) {
            return ResponseEntity.
                    status(HttpStatus.FORBIDDEN).
                    contentType(MediaType.APPLICATION_JSON).
                    body(GSON.toJson(new ModelFoundFailed(ex)));
        }
    }

    @GetMapping("/ui/{fileName}")
    public ResponseEntity<ExcelUi> getExcelUiModel(@PathVariable String fileName) {
        try {
            ExcelUi excelUiModel = excelService.getExcelUiModel(fileName);
            return ResponseEntity.ok(excelUiModel);
        } catch (FileNotFoundException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UiModelGenerationFailed(e));
        }

        return null;
    }
}
