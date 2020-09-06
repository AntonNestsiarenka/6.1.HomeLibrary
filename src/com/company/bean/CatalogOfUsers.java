package com.company.bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CatalogOfUsers {

    public static final String FILE_NAME_OF_CATALOG = "users_data.txt";

    private static CatalogOfUsers catalogOfUsers;

    private final ArrayList<User> users;

    public static CatalogOfUsers createInstance()
    {
        if (catalogOfUsers == null)
        {
            catalogOfUsers = new CatalogOfUsers();
        }
        return catalogOfUsers;
    }

    private CatalogOfUsers()
    {
        users = new ArrayList<>();
    }

    public static CatalogOfUsers getCatalogOfUsers() {
        return catalogOfUsers;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user)
    {
        users.add(user);
    }

    public void catalogInit() throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(FILE_NAME_OF_CATALOG));
        String currentLine;
        Pattern pattern = Pattern.compile("^(.+) \\| (.+) \\| (.+) \\| (.+)$");
        while ((currentLine = bf.readLine()) != null)
        {
            Matcher matcher = pattern.matcher(currentLine);
            if (matcher.find()) {
                users.add(new User(matcher.group(1), matcher.group(2), matcher.group(3), User.TypeOfUser.valueOf(matcher.group(4).toUpperCase())));
            }
        }
        bf.close();
    }
}