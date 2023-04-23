package com.example.tezis.service;

import com.example.tezis.dao.model.excel.ExcelFile;
import com.example.tezis.dao.response.fileController.FileDescription;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public interface FileService {

    int uploadFile(MultipartFile multipartFile);

    int uploadFile(ExcelFile file);

    ExcelFile getFileByName(String fileName);

    ExcelFile getFileById(Integer fileId) throws FileNotFoundException;

    Optional<ExcelFile> getFileByIdOpt(Integer fileId) throws FileNotFoundException;

    List<FileDescription> getAll();

    void deleteFile(ExcelFile file);

    boolean isAssignedToUser(int userId, int fileId);
}
