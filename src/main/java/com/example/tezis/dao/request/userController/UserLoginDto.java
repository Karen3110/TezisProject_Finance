package com.example.tezis.dao.request.userController;

import lombok.Data;

@Data
public class UserLoginDto {
    private String login;

    private String password;
}
