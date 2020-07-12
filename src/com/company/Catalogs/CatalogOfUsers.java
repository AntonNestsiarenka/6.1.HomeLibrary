package com.company.Catalogs;

import java.io.*;
import java.util.ArrayList;

public class CatalogOfUsers {

    public static final String FILE_NAME_OF_CATALOG = "users.txt";
    private static final int KEY = 0b1110100101;

    private static CatalogOfUsers catalogOfUsers;

    private ArrayList<String> usersData;

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
        usersData = new ArrayList<>();
    }

    public void catalogInit() throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME_OF_CATALOG)));
        while (true) {
            String temp = null;
            try {
                temp = bufferedReader.readLine();
            } catch (IOException e) {
                break;
            }
            if (temp != null)
            {
                usersData.add(temp);
            }
            else break;
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CatalogOfUsers getCatalogOfUsers() {
        return catalogOfUsers;
    }

    public ArrayList<String> getUsersData() {
        return usersData;
    }

    public void addNewUser(String[] newUserdata) throws IOException {
        newUserdata[2] = encryptPassword(newUserdata[2]);
        FileWriter fileWriter = new FileWriter(FILE_NAME_OF_CATALOG, true);
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < newUserdata.length - 1; i++)
        {
            temp.append(newUserdata[i] + " | ");
        }
        temp.append(newUserdata[newUserdata.length - 1]);
        String userData = temp.toString();
        usersData.add(userData);
        fileWriter.write("\n" + userData);
        fileWriter.flush();
        fileWriter.close();
    }

    private String decryptPassword(String password)
    {
        StringBuilder decryptedPassword = new StringBuilder();
        for (int i = 0; i < password.length(); i++)
        {
            int symbol = password.charAt(i) ^ KEY;
            decryptedPassword.append((char)(symbol));
        }
        return decryptedPassword.toString();
    }

    private String encryptPassword(String password)
    {
        StringBuilder encryptedPassword = new StringBuilder();
        for (int i = 0; i < password.length(); i++)
        {
            int symbol = password.charAt(i) ^ KEY;
            encryptedPassword.append((char)(symbol));
        }
        return encryptedPassword.toString();
    }

    public String[] getLogInData(String userName)
    {
        for (String userData : usersData)
        {
            String[] temp = userData.split(" \\| ");
            if (temp[0].equals(userName) || temp[1].equals(userName))
            {
                return temp;
            }
        }
        return null;
    }

    public boolean isCorrectPassword(String encryptedPassword, String userPasswordInput)
    {
        return userPasswordInput.equals(decryptPassword(encryptedPassword));
    }
}