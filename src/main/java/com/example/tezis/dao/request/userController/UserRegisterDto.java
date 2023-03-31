package com.example.tezis.dao.request.userController;

public class UserRegisterDto {
    private String login;

    private String password;

    //todo: karen: implement registration process for user details too. get data from register form from UI.
    // like name, surname, age, gender, e-mail etc.


    public UserRegisterDto(String login, String password) {
        this.login = login;
        this.password = password;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
