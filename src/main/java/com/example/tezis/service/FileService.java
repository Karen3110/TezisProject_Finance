package com.example.tezis.service;

import com.example.tezis.dao.model.excel.ExcelFile;
import com.example.tezis.dao.response.fileController.FileDescription;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    int uploadFile(MultipartFile multipartFile);

    ExcelFile getFileByName(String fileName);

    ExcelFile getFileById(Integer fileId);

    List<FileDescription> getAll();

}
