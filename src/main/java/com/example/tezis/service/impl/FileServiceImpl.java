package com.example.tezis.service.impl;

import com.example.tezis.dao.model.excel.ExcelFile;
import com.example.tezis.dao.response.fileController.FileDescription;
import com.example.tezis.repository.FileRepository;
import com.example.tezis.repository.UserDetailsRepository;
import com.example.tezis.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    private final UserDetailsRepository userDetailsRepository;


    public FileServiceImpl(FileRepository fileRepository, UserDetailsRepository userDetailsRepository) {
        this.fileRepository = fileRepository;
        this.userDetailsRepository = userDetailsRepository;
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
        return saveFile(file);
    }

    @Override
    @Transactional(readOnly = false)
    public int uploadFile(ExcelFile file) {
        return saveFile(file);
    }


    private int saveFile(ExcelFile file) {
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
    public ExcelFile getFileById(Integer fileId) throws FileNotFoundException {
        return fileRepository.findById(fileId).orElseThrow(() -> new FileNotFoundException("File with id:" + fileId + " not found"));
    }

    @Override
    public Optional<ExcelFile> getFileByIdOpt(Integer fileId) throws FileNotFoundException {
        return fileRepository.findById(fileId);
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

    @Override
    public void deleteFile(ExcelFile file) {
        fileRepository.delete(file);
    }

    @Override
    public boolean isAssignedToUser(int userId, int fileId) {
        List<Object> assignedFileId = userDetailsRepository.getAssignedFileId(userId, fileId);

        return !assignedFileId.isEmpty();
    }


}
