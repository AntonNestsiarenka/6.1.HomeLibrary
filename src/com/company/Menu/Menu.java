package com.company.Menu;

import com.company.Book.Book;
import com.company.Catalogs.CatalogOfBook;
import com.company.Catalogs.CatalogOfUsers;
import com.company.User.User;
import com.company.Utils.InputUtils;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

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
    private CatalogOfBook catalogOfBook;
    private User user;
    private Scanner scanner;

    public Menu()
    {
        catalogOfUsers = CatalogOfUsers.createInstance();
        catalogOfBook = CatalogOfBook.createInstance();
        scanner = new Scanner(System.in);
    }

    public void startMenu()
    {
        System.out.println("Welcome to Home Library App!\n");
        boolean logic = true;
        while (logic) {
            System.out.println("Start command menu:");
            System.out.println(START_MENU);
            String command = InputUtils.inputStr("Enter command: ", scanner);
            switch (command.toLowerCase()) {
                case ("1"):
                case ("login"): {
                    login();
                    if (user.getTypeOfUser() == User.TypeOfUser.USER) {
                        userMenu();
                    }
                    else if (user.getTypeOfUser() == User.TypeOfUser.ADMINISTRATOR) {
                        adminMenu();
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
                    System.out.println("Description of all commands:\n" +
                            "1. Login - login using username and password.\n" +
                            "2. Registration - new user registration.\n" +
                            "3. Exit - application shutdown.\n" +
                            "4. Help - description of all commands.\n" +
                            "Commands can be entered case insensitive or using numbers.\n");
                    break;
                }
                default: {
                    System.out.println("Such a console command does not exist. For help, type help in the console.");
                }
            }
        }
    }

    private void searchMenu()
    {
        System.out.println("Search command menu:");
        System.out.println("1. Find by name\n" +
                "2. Find by author\n");
        String command = InputUtils.inputStr("Enter command: ", scanner);
        switch (command.toLowerCase()) {
            case ("1"):
            case ("find by name"):
            {
                String bookName = InputUtils.inputStr("Enter book name: ", scanner);
                System.out.println("Found books:");
                CatalogOfBook.showBooks(catalogOfBook.findByName(bookName));
                break;
            }
            case ("2"):
            case ("find by author"):
            {
                String authorName = InputUtils.inputStr("Enter author name: ", scanner);
                System.out.println("Found books:");
                CatalogOfBook.showBooks(catalogOfBook.findByAuthor(authorName));
                break;
            }
        }
    }

    private void userMenu()
    {
        boolean logic = true;
        while(logic) {
            System.out.println("User command menu:");
            System.out.println(USER_MENU);
            String command = InputUtils.inputStr("Enter command: ", scanner);
            switch (command.toLowerCase()) {
                case ("1"):
                case ("show books"): {
                    catalogOfBook.showCatalog();
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
                    System.out.println("You are logout.\n");
                    return;
                }
                case ("4"):
                case ("exit"): {
                    scanner.close();
                    System.exit(0);
                }
                case ("5"):
                case ("help"): {
                    System.out.println("Description of all commands:\n" +
                            "1. Show books - show all books from home library.\n" +
                            "2. Search books - searches for a given book and displays the search results on the screen.\n" +
                            "3. Logout - logout from account.\n" +
                            "4. Exit - application shutdown.\n" +
                            "5. Help - description of all commands.\n" +
                            "Commands can be entered case insensitive or using numbers.\n");
                    break;
                }
                default: {
                    System.out.println("Such a console command does not exist. For help, type help in the console.");
                }
            }
        }
    }

    private void adminMenu()
    {
        boolean logic = true;
        while(logic) {
            System.out.println("Administrator command menu:");
            System.out.println(ADMIN_MENU);
            String command = InputUtils.inputStr("Enter command: ", scanner);
            switch (command.toLowerCase()) {
                case ("1"):
                case ("show books"): {
                    catalogOfBook.showCatalog();
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
                    System.out.println("You are logout.\n");
                    return;
                }
                case ("6"):
                case ("exit"): {
                    scanner.close();
                    System.exit(0);
                }
                case ("7"):
                case ("help"): {
                    System.out.println("Description of all commands:\n" +
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
                    System.out.println("Such a console command does not exist. For help, type help in the console.");
                }
            }
        }
    }

    private void addBook()
    {
        String bookName = InputUtils.inputNotEmptyStr("Enter book name: ", scanner);
        int countAuthors = InputUtils.inputCountOfAuthors("Enter count of authors: ", scanner);
        String[] authorNames = new String[countAuthors];
        for (int i = 0; i < countAuthors; i++)
        {
            authorNames[i] = InputUtils.inputNotEmptyStr("Enter author name" + " " + (i + 1) + ": ", scanner);
        }
        int yearOfRelease = InputUtils.inputYearOfRelease("Enter year of release: ", scanner);
        int countOfPages = InputUtils.inputCountOfPages("Enter count of pages: ", scanner);
        String typeOfBook = InputUtils.inputTypeOfBook("Enter typeOfBook (paper or electronic): ", scanner);
        System.out.println("Book " + ((catalogOfBook.add(new Book(bookName, authorNames, yearOfRelease, countOfPages, Book.TypeOfBook.valueOf(typeOfBook)))) ? "added" : "not added"));
    }

    private void removeBook()
    {
        catalogOfBook.showCatalog();
        int numberOfBook = InputUtils.inputInt("Enter number of book for removing: ", scanner);
        if (numberOfBook > 0 && numberOfBook <= catalogOfBook.getBooks().size())
        {
            System.out.println("Book " + ((catalogOfBook.remove(catalogOfBook.getBooks().get(numberOfBook - 1))) ? "removed" : "not removed"));
        }
        else
        {
            System.out.println("Books with this number do not exist in the catalog");
            System.out.println("Removing failed.");
            return;
        }
    }

    private void login()
    {
        while (true) {
            String userNameInput = InputUtils.inputStr("Enter your user name or email address: ", scanner);
            String passwordInput = InputUtils.inputStr("Enter your password: ", scanner);
            String[] userData = catalogOfUsers.getLogInData(userNameInput);
            if (userData != null)
            {
                if (catalogOfUsers.isCorrectPassword(userData[2], passwordInput)) {
                    user = new User(userData[0], userData[1], User.TypeOfUser.valueOf(userData[3].toUpperCase()));
                    System.out.println("Welcome " + user.getUserName() + ". You are logged in.\n");
                    break;
                }
                System.out.println("User name or password is incorrect\n");
            }
            else
                System.out.println("User name or password is incorrect\n");
        }
    }

    private void registration()
    {
        String userName = InputUtils.inputUserName("Enter user name: ", scanner);
        String userEmail = InputUtils.inputEmailAddress("Enter email address: ", scanner);
        String userPassword = InputUtils.inputUserPassword("Enter password: ", scanner);
        String[] tempUserData = catalogOfUsers.getLogInData(userName);
        if (catalogOfUsers.getLogInData(userName) == null)
        {
            if (catalogOfUsers.getLogInData(userEmail) == null)
            {
                String[] userData = new String[] {userName, userEmail, userPassword, "user"};
                try {
                    catalogOfUsers.addNewUser(userData);
                    System.out.println("New user registration completed successfully.");
                } catch (IOException e) {
                    System.out.println("Error adding new user.");
                    System.out.println(e.getMessage());
                    System.out.println("Registration failed.");
                }
            }
            else
            {
                System.out.println("User with this email address already exists");
                System.out.println("Registration failed. Try again with new email address.");
            }
        }
        else
        {
            System.out.println("User with this user name already exists");
            System.out.println("Registration failed. Try again with new user name.");
        }
    }
}