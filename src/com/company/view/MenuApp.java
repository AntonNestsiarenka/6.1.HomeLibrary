package com.company.view;

import com.company.bean.Book;
import com.company.bean.CatalogOfBooks;
import com.company.bean.CatalogOfUsers;
import com.company.bean.User;
import com.company.logic.CatalogOfBooksLogic;
import com.company.logic.CatalogOfUsersLogic;
import com.company.logic.InputUserLogic;
import java.io.IOException;
import java.util.Scanner;

public class MenuApp {

    public static final String START_MENU = "1. Login\n" +
            "2. Registration\n" +
            "3. Exit\n" +
            "4. Help\n";
    public static final String USER_MENU = "1. Show books\n" +
            "2. Search books\n" +
            "3. Logout\n" +
            "4. Exit\n" +
            "5. Help\n";
    public static final String ADMIN_MENU = "1. Show books\n" +
            "2. Search books\n" +
            "3. Add book\n" +
            "4. Remove book\n" +
            "5. Logout\n" +
            "6. Exit\n" +
            "7. Help\n";

    private CatalogOfUsers catalogOfUsers;
    private CatalogOfBooks catalogOfBooks;
    private CatalogOfBooksLogic catalogOfBooksLogic;
    private CatalogOfUsersLogic catalogOfUsersLogic;
    private User user;
    private Scanner scanner;
    private Viewer viewer;

    public MenuApp(CatalogOfUsers catalogOfUsers, CatalogOfBooks catalogOfBooks, CatalogOfBooksLogic catalogOfBooksLogic, CatalogOfUsersLogic catalogOfUsersLogic, Viewer viewer)
    {
        this.catalogOfUsers = catalogOfUsers;
        this.catalogOfBooks = catalogOfBooks;
        this.catalogOfBooksLogic = catalogOfBooksLogic;
        this.catalogOfUsersLogic = catalogOfUsersLogic;
        scanner = new Scanner(System.in);
        this.viewer = viewer;
    }

    public void startMenu()
    {
        viewer.printMessage("Welcome to Home Library App!\n");
        boolean logic = true;
        while (logic) {
            viewer.printMessage("Start command menu:");
            viewer.printMessage(START_MENU);
            String command = InputUserLogic.inputStr("Enter command: ", scanner);
            switch (command.toLowerCase()) {
                case ("1"):
                case ("login"): {
                    if (login())
                    {
                        viewer.printMessage("Welcome " + user.getUserName() + ". You are logged in.\n");
                        if (user.getTypeOfUser() == User.TypeOfUser.USER) {
                            userMenu();
                        }
                        else if (user.getTypeOfUser() == User.TypeOfUser.ADMINISTRATOR) {
                            adminMenu();
                        }
                    }
                    break;
                }
                case ("2"):
                case ("registration"): {
                    registration();
                    break;
                }
                case ("3"):
                case ("exit"): {
                    logic = false;
                    scanner.close();
                    break;
                }
                case ("4"):
                case ("help"): {
                    viewer.printMessage("Description of all commands:\n" +
                            "1. Login - login using username and password.\n" +
                            "2. Registration - new user registration.\n" +
                            "3. Exit - application shutdown.\n" +
                            "4. Help - description of all commands.\n" +
                            "Commands can be entered case insensitive or using numbers.\n");
                    break;
                }
                default: {
                    viewer.printMessage("Such a console command does not exist. For help, type help in the console.");
                }
            }
        }
    }

    private void searchMenu()
    {
        viewer.printMessage("Search command menu:");
        viewer.printMessage("1. Find by name\n" +
                "2. Find by author\n");
        String command = InputUserLogic.inputStr("Enter command: ", scanner);
        switch (command.toLowerCase()) {
            case ("1"):
            case ("find by name"):
            {
                String bookNameInput = InputUserLogic.inputStr("Enter book name: ", scanner);
                viewer.printMessage("Found books:");
                catalogOfBooksLogic.showBooks(catalogOfBooksLogic.findBooksByName(catalogOfBooks, bookNameInput));
                break;
            }
            case ("2"):
            case ("find by author"):
            {
                String authorNameInput = InputUserLogic.inputStr("Enter author name: ", scanner);
                viewer.printMessage("Found books:");
                catalogOfBooksLogic.showBooks(catalogOfBooksLogic.findByAuthor(catalogOfBooks, authorNameInput));
                break;
            }
        }
    }

    private void userMenu()
    {
        boolean logic = true;
        while(logic) {
            viewer.printMessage("User command menu:");
            viewer.printMessage(USER_MENU);
            String command = InputUserLogic.inputStr("Enter command: ", scanner);
            switch (command.toLowerCase()) {
                case ("1"):
                case ("show books"): {
                    catalogOfBooksLogic.showBookCatalog(catalogOfBooks);
                    break;
                }
                case ("2"):
                case ("search books"): {
                    searchMenu();
                    break;
                }
                case ("3"):
                case ("logout"): {
                    user = null;
                    viewer.printMessage("You are logout.\n");
                    return;
                }
                case ("4"):
                case ("exit"): {
                    scanner.close();
                    System.exit(0);
                }
                case ("5"):
                case ("help"): {
                    viewer.printMessage("Description of all commands:\n" +
                            "1. Show books - show all books from home library.\n" +
                            "2. Search books - searches for a given book and displays the search results on the screen.\n" +
                            "3. Logout - logout from account.\n" +
                            "4. Exit - application shutdown.\n" +
                            "5. Help - description of all commands.\n" +
                            "Commands can be entered case insensitive or using numbers.\n");
                    break;
                }
                default: {
                    viewer.printMessage("Such a console command does not exist. For help, type help in the console.");
                }
            }
        }
    }

    private void adminMenu()
    {
        boolean logic = true;
        while(logic) {
            viewer.printMessage("Administrator command menu:");
            viewer.printMessage(ADMIN_MENU);
            String command = InputUserLogic.inputStr("Enter command: ", scanner);
            switch (command.toLowerCase()) {
                case ("1"):
                case ("show books"): {
                    catalogOfBooksLogic.showBookCatalog(catalogOfBooks);
                    break;
                }
                case ("2"):
                case ("search books"): {
                    searchMenu();
                    break;
                }
                case ("3"):
                case ("add book"): {
                    addBook();
                    break;
                }
                case ("4"):
                case ("remove book"): {
                    removeBook();
                    break;
                }
                case ("5"):
                case ("logout"): {
                    user = null;
                    viewer.printMessage("You are logout.\n");
                    return;
                }
                case ("6"):
                case ("exit"): {
                    scanner.close();
                    System.exit(0);
                }
                case ("7"):
                case ("help"): {
                    viewer.printMessage("Description of all commands:\n" +
                            "1. Show books - show all books from home library.\n" +
                            "2. Search books - searches for a given book and displays the search results on the screen.\n" +
                            "3. Add book - add book to home library.\n" +
                            "4. Remove book - remove book from home library.\n" +
                            "5. Logout - logout from account.\n" +
                            "6. Exit - application shutdown.\n" +
                            "7. Help - description of all commands.\n" +
                            "Commands can be entered case insensitive or using numbers.\n");
                    break;
                }
                default: {
                    viewer.printMessage("Such a console command does not exist. For help, type help in the console.");
                }
            }
        }
    }

    private void addBook()
    {
        // Добавляет заданную пользователем книгу в каталог книг.
        String bookNameInput = InputUserLogic.inputNotEmptyStr("Enter book name: ", scanner);
        int countAuthorsInput = InputUserLogic.inputCountOfAuthors("Enter count of authors: ", scanner);
        String[] authorNames = new String[countAuthorsInput];
        for (int i = 0; i < countAuthorsInput; i++)
        {
            authorNames[i] = InputUserLogic.inputNotEmptyStr("Enter author name" + " " + (i + 1) + ": ", scanner);
        }
        int yearOfReleaseInput = InputUserLogic.inputYearOfRelease("Enter year of release: ", scanner);
        int countOfPagesInput = InputUserLogic.inputCountOfPages("Enter count of pages: ", scanner);
        String typeOfBookInput = InputUserLogic.inputTypeOfBook("Enter typeOfBook (paper or electronic): ", scanner);
        Book bookForAdd = new Book(bookNameInput, authorNames, yearOfReleaseInput, countOfPagesInput, Book.TypeOfBook.valueOf(typeOfBookInput));
        try {
            catalogOfBooksLogic.addBookToCatalog(catalogOfBooks, bookForAdd);
        } catch (IOException e) {
            viewer.printMessage("Error adding new book to catalog of books.");
            viewer.printMessage(e.getMessage());
            viewer.printMessage("Book was not added.");
        }
    }

    private void removeBook()
    {
        // Удаляет заданную пользователем книгу из каталога книг.
        catalogOfBooksLogic.showBookCatalog(catalogOfBooks);
        int numberOfBookInput = InputUserLogic.inputInt("Enter number of book for removing: ", scanner);
        if (numberOfBookInput > 0 && numberOfBookInput <= catalogOfBooks.getBooks().size())
        {
            try {
                catalogOfBooksLogic.removeBookFromCatalog(catalogOfBooks, catalogOfBooks.getBooks().get(numberOfBookInput - 1));
            } catch (IOException e) {
                viewer.printMessage("Error removing adding book from catalog of books.");
                viewer.printMessage(e.getMessage());
                viewer.printMessage("Book was not removed.");
            }
        }
        else
        {
            viewer.printMessage("Books with this number do not exist in the catalog");
        }
    }

    private boolean login()
    {
        // Вход пользователя в систему. Возвращает булевую произошел ли вход в систему.
        String userNameInput = InputUserLogic.inputStr("Enter your user name or email address: ", scanner);
        String passwordInput = InputUserLogic.inputStr("Enter your password: ", scanner);
        User user = catalogOfUsersLogic.getUserByUserNameOrEmail(catalogOfUsers, userNameInput);
        if (user != null)
        {
            if (catalogOfUsersLogic.isCorrectPassword(user.getPassword(), passwordInput)) {
                this.user = user;
                return true;
            }
            viewer.printMessage("User name or password is incorrect\n");
        }
        else
            viewer.printMessage("User name or password is incorrect\n");
        return false;
    }

    private void registration()
    {
        // Регистрация нового пользователя в системе.
        String userNameInput = InputUserLogic.inputUserName("Enter user name: ", scanner);
        String userEmailInput = InputUserLogic.inputEmailAddress("Enter email address: ", scanner);
        String userPasswordInput = InputUserLogic.inputUserPassword("Enter password: ", scanner);
        User user = catalogOfUsersLogic.getUserByUserNameOrEmail(catalogOfUsers, userNameInput);
        if (user == null)
        {
            if (catalogOfUsersLogic.getUserByUserNameOrEmail(catalogOfUsers, userEmailInput) == null)
            {
                user.setPassword(catalogOfUsersLogic.encryptPassword(userPasswordInput));
                try {
                    catalogOfUsersLogic.addNewUserToCatalog(catalogOfUsers, user);
                    viewer.printMessage("New user registration completed successfully.");
                } catch (IOException e) {
                    viewer.printMessage("Error adding new user to catalog of users.");
                    viewer.printMessage(e.getMessage());
                    viewer.printMessage("Registration failed.");
                }
            }
            else
            {
                viewer.printMessage("User with this email address already exists.");
                viewer.printMessage("Registration failed. Try again with new email address.");
            }
        }
        else
        {
            viewer.printMessage("User with this user name already exists.");
            viewer.printMessage("Registration failed. Try again with new user name.");
        }
    }
}