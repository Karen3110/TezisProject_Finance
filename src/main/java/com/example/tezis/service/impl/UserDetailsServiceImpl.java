package com.example.tezis.service.impl;

import com.example.tezis.dao.model.excel.ExcelFile;
import com.example.tezis.dao.model.user.UserDetails;
import com.example.tezis.dao.request.userController.UserLoginDto;
import com.example.tezis.dao.request.userController.UserRegisterDto;
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
import java.util.Optional;


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

        //todo: karen: implement saving of user data.(name, surname, etc.)
        UserDetails userDetails = new UserDetails("NAME", "SURNAME");
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
    public void assignFileToUser(int userId, int fileId) throws UserNotFoundException, FileNotFoundException {
        Optional<UserDetails> userById = userDetailsRepository.findById(userId);
        if (userById.isEmpty()) {
            throw new UserNotFoundException("User with id:" + userId + " not found.");
        }

        ExcelFile fileById = fileService.getFileById(fileId);
        if (fileById == null) {
            throw new FileNotFoundException("File with id:" + fileId + " not found.");
        }

        userDetailsRepository.assignFileToUser(userId, fileId);
    }
}
