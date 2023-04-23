package com.example.tezis.service.impl;

import com.example.tezis.dao.model.excel.ExcelFile;
import com.example.tezis.dao.model.user.UserDetails;
import com.example.tezis.dao.request.userController.UserLoginDto;
import com.example.tezis.dao.request.userController.UserRegisterDto;
import com.example.tezis.dao.response.fileController.FileDescription;
import com.example.tezis.dao.response.userController.RegistrationSuccess;
import com.example.tezis.repository.UserDetailsRepository;
import com.example.tezis.service.FileService;
import com.example.tezis.service.UserDetailsService;
import com.example.tezis.service.UserLoginPasswordService;
import com.example.tezis.util.exceptions.UserNotFoundException;
import com.example.tezis.util.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserLoginPasswordService loginPasswordService;

    @Autowired
    private FileService fileService;

    @Override
    @Transactional(readOnly = false)
    public RegistrationSuccess registerUser(UserRegisterDto userData, boolean isAdmin) throws UserNotFoundException {

        int registeredLoginId = loginPasswordService.saveUser(
                userData.getLogin(),
                userData.getPassword(),
                isAdmin ? UserRole.ADMIN : UserRole.USER);

        UserDetails userDetails = new UserDetails(userData.getName(), userData.getSurname());
        userDetails.setUserLoginId(registeredLoginId);

        UserDetails savedUser = userDetailsRepository.save(userDetails);

        //todo: karen: implement token for users.?
        return new RegistrationSuccess(savedUser.getId(), "SOME TOKEN");
    }

    @Override
    @Transactional(readOnly = false)
    public UserDetails login(UserLoginDto loginData) throws UserNotFoundException {

        int loggedUserDetailsId = loginPasswordService.makeActiveUser(loginData);
        UserDetails loggedUser = userDetailsRepository.getUserDetailsByUserLoginId(loggedUserDetailsId);

        return loggedUser;

    }

    @Override
    @Transactional(readOnly = false)
    public void logout(int id) throws UserNotFoundException {
        UserDetails userDetails = userDetailsRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Unable to find user."));
        loginPasswordService.logout(userDetails);
    }

    @Override
    @Transactional(readOnly = false)
    public int assignFileToUser(int userId, int fileId) throws UserNotFoundException, FileNotFoundException,IllegalStateException {
        UserDetails userDetails = userDetailsRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id:" + userId + " not found."));

        if (fileService.isAssignedToUser(userId,fileId)) {
            throw new IllegalStateException("File already assigned to user");
        }

        ExcelFile fileById = fileService
                .getFileByIdOpt(fileId)
                .orElseThrow(() -> new FileNotFoundException("File with id:" + fileId + "is not found"));

        ExcelFile copy = fileById.copy();
        copy.setUserAssigned(true);
        int fileIndexToAssign = fileService.uploadFile(copy);


        userDetailsRepository.assignFileToUser(userId, fileIndexToAssign, fileId);
        return fileIndexToAssign;
    }

    @Override
    public List<FileDescription> getAllAssigned(int userId) throws UserNotFoundException {
        UserDetails userById = userDetailsRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id:" + userId + " not found."));


        List<ExcelFile> filledFiles = userById.getFilledFiles();
        return filledFiles
                .stream()
                .map(item -> new FileDescription(item.getId(), item.getFileName()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false)
    public void releaseFileFromUser(int userId, int fileId) throws UserNotFoundException, FileNotFoundException {
        UserDetails userById = userDetailsRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id:" + userId + " not found."));

        List<ExcelFile> filledFiles = userById.getFilledFiles();
        ExcelFile fileToDelete = filledFiles
                .stream()
                .filter(item -> item.getId() == fileId)
                .findFirst().orElseThrow(() -> new FileNotFoundException("File with id:" + fileId + "is not assigned to user"));
        filledFiles.remove(fileToDelete);
        fileService.deleteFile(fileToDelete);

    }
}
