package com.company.User;

import java.util.Objects;

public class User {

    private String userName;
    private String email;
    private TypeOfUser typeOfUser;

    public User(String userName, String email, TypeOfUser typeOfUser)
    {
        this.userName = userName;
        this.email = email;
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
        return userName.equals(user.userName) &&
                email.equals(user.email) &&
                typeOfUser == user.typeOfUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, email, typeOfUser);
    }

    public enum TypeOfUser {

        USER,
        ADMINISTRATOR
    }
}