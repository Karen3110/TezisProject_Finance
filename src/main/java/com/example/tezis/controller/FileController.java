package com.example.tezis.controller;

import com.example.tezis.dao.model.excel.ExcelFile;
import com.example.tezis.dao.response.fileController.FileDescription;
import com.example.tezis.dao.response.fileController.UploadFailed;
import com.example.tezis.dao.response.fileController.UploadSuccess;
import com.example.tezis.service.FileService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController()
@RequestMapping("/file")
public class FileController {


    final FileService fileService;
    @Autowired
    private Gson GSON;

    public FileController(FileService excelWorkService) {
        this.fileService = excelWorkService;
    }

    @PostMapping(
            value = "/upload",
            produces = "application/json; charset=utf-8"
    )
    ResponseEntity<String> uploadFIle(@RequestParam("file") MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        if (fileName != null && fileName.endsWith(".xlsx")) {
            int savedId = fileService.uploadFile(multipartFile);
            if (savedId != -1) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(GSON.toJson(new UploadSuccess(savedId, fileName)));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(GSON.toJson(new UploadFailed(fileName)));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GSON.toJson(new UploadFailed(fileName, "file format not supported.")));
        }
    }


    @GetMapping("/download/{fileId}")
    ResponseEntity<byte[]> downloadExcelFileById(@PathVariable Integer fileId) {
        ExcelFile file = fileService.getFileById(fileId);
        if (file != null) {
            return ResponseEntity.status(HttpStatus.OK).headers(getHeaders(file)).body(file.getData());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/models")
    ResponseEntity<List<FileDescription>> getAllExcelFiles() {
        List<FileDescription> allFilesDescription = fileService.getAll();
        return ResponseEntity.ok(allFilesDescription);
    }

    HttpHeaders getHeaders(ExcelFile file) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE);
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"");
        return httpHeaders;
    }
}
