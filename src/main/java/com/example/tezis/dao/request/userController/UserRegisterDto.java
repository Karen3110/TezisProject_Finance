package com.example.tezis.dao.request.userController;

public class UserRegisterDto {
    private String login;

    private String password;

    private String name;

    private String surname;


    //todo: karen: implement registration process for user details too. get data from register form from UI.
    // like name, surname, age, gender, e-mail etc.


    public UserRegisterDto(String login, String password, String name, String surname) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
