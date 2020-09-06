package com.company;

import com.company.bean.ApplicationInitializationException;
import com.company.bean.CatalogOfBooks;
import com.company.bean.CatalogOfUsers;
import com.company.logic.CatalogOfBooksLogic;
import com.company.logic.CatalogOfUsersLogic;
import com.company.view.MenuApp;
import com.company.view.Viewer;

import java.io.IOException;

public class HomeLibraryMain {

    public static void main(String[] args) throws ApplicationInitializationException {
        CatalogOfBooks catalogOfBooks = CatalogOfBooks.createInstance();
        CatalogOfUsers catalogOfUsers = CatalogOfUsers.createInstance();
        Viewer viewer = new Viewer();
        try {
            catalogOfBooks.catalogInit();
        } catch (IOException e) {
            viewer.printErrorMessage(e.getMessage());
            viewer.printErrorMessage("Initialisation catalog of book failed.");
            throw new ApplicationInitializationException("Application initialization failed", e);
        }
        try {
            catalogOfUsers.catalogInit();
        } catch (IOException e) {
            viewer.printErrorMessage(e.getMessage());
            viewer.printErrorMessage("Initialisation catalog of users failed.");
            throw new ApplicationInitializationException("Application initialization failed", e);
        }

        CatalogOfBooksLogic catalogOfBooksLogic = new CatalogOfBooksLogic(viewer);
        CatalogOfUsersLogic catalogOfUsersLogic = new CatalogOfUsersLogic(viewer);

        MenuApp menuApp = new MenuApp(catalogOfUsers, catalogOfBooks, catalogOfBooksLogic, catalogOfUsersLogic, viewer);
        menuApp.startMenu();
    }
}