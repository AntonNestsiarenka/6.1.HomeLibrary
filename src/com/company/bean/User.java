package com.company.bean;

import java.util.Objects;

public class User {

    private String userName;
    private String email;
    private String password;
    private TypeOfUser typeOfUser;

    public User(String userName, String email, String password, TypeOfUser typeOfUser)
    {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.typeOfUser = typeOfUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TypeOfUser getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(TypeOfUser typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                typeOfUser == user.typeOfUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, email, password, typeOfUser);
    }

    public enum TypeOfUser {

        USER,
        ADMINISTRATOR
    }
}