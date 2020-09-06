package com.company.logic;

import com.company.bean.CatalogOfUsers;
import com.company.bean.User;
import com.company.view.Viewer;
import java.io.FileWriter;
import java.io.IOException;

public class CatalogOfUsersLogic {

    private Viewer viewer;
    private static final int KEY = 0b1110100101;

    public CatalogOfUsersLogic()
    {
        viewer = new Viewer();
    }

    public CatalogOfUsersLogic(Viewer viewer)
    {
        this.viewer = viewer;
    }

    public void addNewUserToCatalog(CatalogOfUsers catalogOfUsers, User user) throws IOException {
        // Добавляет нового пользователя в каталог всех пользователей.
        FileWriter fileWriter = new FileWriter(CatalogOfUsers.FILE_NAME_OF_CATALOG, true);
        StringBuilder userData = new StringBuilder();
        userData.append(user.getUserName() + " | ");
        userData.append(user.getEmail() + " | ");
        userData.append(user.getPassword() + " | ");
        userData.append(user.getTypeOfUser().toString());
        fileWriter.write("\n" + userData.toString());
        fileWriter.flush();
        fileWriter.close();
        catalogOfUsers.addUser(user);
    }

    private String decryptPassword(String password)
    {
        // Возвращает расшифрованый пароль.
        StringBuilder decryptedPassword = new StringBuilder();
        for (int i = 0; i < password.length(); i++)
        {
            int symbol = password.charAt(i) ^ KEY;
            decryptedPassword.append((char)(symbol));
        }
        return decryptedPassword.toString();
    }

    public String encryptPassword(String password)
    {
        // Возвращает зашифрованый пароль.
        StringBuilder encryptedPassword = new StringBuilder();
        for (int i = 0; i < password.length(); i++)
        {
            int symbol = password.charAt(i) ^ KEY;
            encryptedPassword.append((char)(symbol));
        }
        return encryptedPassword.toString();
    }

    public User getUserByUserNameOrEmail(CatalogOfUsers catalogOfUsers, String userNameOrEmail)
    {
        /* Возвращает пользователя с заданным именем пользователя или емейлом. Если такого пользователя нет в каталоге
           пользователей то возвращает null. */
        for (User user : catalogOfUsers.getUsers())
        {
            if (user.getUserName().equals(userNameOrEmail) || user.getEmail().equals(userNameOrEmail))
            {
                return user;
            }
        }
        return null;
    }

    public boolean isCorrectPassword(String encryptedPassword, String userPasswordInput)
    {
        return userPasswordInput.equals(decryptPassword(encryptedPassword));
    }

}
