package com.example.tezis.service.impl;

import com.example.tezis.dao.model.excel.ExcelFile;
import com.example.tezis.dao.response.fileController.FileDescription;
import com.example.tezis.repository.FileRepository;
import com.example.tezis.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class FileServiceImpl implements FileService {

    final
    FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    @Override
    @Transactional(readOnly = false)
    public int uploadFile(MultipartFile multipartFile) {

        ExcelFile file = null;
        try {

            String originalFilename = multipartFile.getOriginalFilename();
            byte[] bytes = multipartFile.getBytes();

            file = new ExcelFile(originalFilename, bytes);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        if (file == null) {
            return -1;
        } else {
            return fileRepository.save(file).getId();
        }

    }

    @Override
    public ExcelFile getFileByName(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return null;
        }
        return fileRepository.findByFileName(fileName);
    }

    @Override
    public ExcelFile getFileById(Integer fileId) {
        return fileRepository.findById(fileId).orElse(null);
    }

    @Override
    public List<FileDescription> getAll() {
        List<ExcelFile> all = fileRepository.findAll();

        List<FileDescription> mappedFileDescription = all
                .stream()
                .map((item) -> new FileDescription(item.getId(), item.getFileName()))
                .collect(Collectors.toList());

        return mappedFileDescription;
    }



}
