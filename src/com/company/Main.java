package com.company;

import com.company.Catalogs.CatalogOfBook;
import com.company.Catalogs.CatalogOfUsers;
import com.company.Menu.Menu;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        CatalogOfBook catalogOfBook = CatalogOfBook.createInstance();
        try {
            catalogOfBook.catalogInit();
        } catch (FileNotFoundException e) {
            System.out.println("Initialisation catalog of book failed.");
            System.out.println(e.getMessage());
            return;
        }
        CatalogOfUsers catalogOfUsers = CatalogOfUsers.createInstance();
        try {
            catalogOfUsers.catalogInit();
        } catch (FileNotFoundException e) {
            System.out.println("Initialisation catalog of users failed.");
            e.getMessage();
            return;
        }
        Menu menu = new Menu();
        menu.startMenu();
    }
}