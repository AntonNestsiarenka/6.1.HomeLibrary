package com.company.logic;

import com.company.bean.Book;

import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputUserLogic {

    public static String inputNotEmptyStr(String str, Scanner scanner)
    {
        // Метод для ввода не пустой строки из консоли.
        System.out.print(str);
        String text = scanner.nextLine().trim();
        if (text.equals(""))
        {
            System.out.println("The string must not be empty.");
            return inputNotEmptyStr(str, scanner);
        }
        return text;
    }

    public static int inputInt(String str, Scanner scanner)
    {
        // Метод для ввода целочисленного значения из консоли.
        System.out.print(str);
        String text = scanner.nextLine();
        try {
            int number = Integer.parseInt(text);
            return number;
        }
        catch (NumberFormatException e)
        {
            System.out.println("Entered value must be integer.");
            return inputInt(str, scanner);
        }
    }

    public static int inputCountOfAuthors(String str, Scanner scanner)
    {
        // Метод для ввода количества авторов книги из консоли. Допустимые значения от 1 до 6 включительно.
        System.out.print(str);
        String text = scanner.nextLine();
        int number;
        try {
            number = Integer.parseInt(text);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Entered value must be integer.");
            return inputCountOfAuthors(str, scanner);
        }
        if (!(number >= 1 && number <= 6))
        {
            System.out.println("Entered number must be: 1 <= value <= 6.");
            return inputCountOfAuthors(str, scanner);
        }
        return number;
    }

    public static int inputYearOfRelease(String str, Scanner scanner)
    {
        // Метод для ввода года выпуска книги из консоли. Допустимые значения от 1800 до текущего года включительно.
        System.out.print(str);
        String text = scanner.nextLine();
        int number;
        try {
            number = Integer.parseInt(text);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Entered value must be integer.");
            return inputYearOfRelease(str, scanner);
        }
        Date date = new Date(System.currentTimeMillis());
        if (!(number >= 1800 && number <= date.getYear() + 1900))
        {
            System.out.println("Entered year must be >= 1800 and <= current year.");
            return inputYearOfRelease(str, scanner);
        }
        return number;
    }

    public static int inputCountOfPages(String str, Scanner scanner)
    {
        // Метод для ввода количества страниц книги из консоли. Допустимые значения от 50 до 2000.
        System.out.print(str);
        String text = scanner.nextLine();
        int number;
        try {
            number = Integer.parseInt(text);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Entered value must be integer.");
            return inputCountOfPages(str, scanner);
        }
        if (!(number >= 50 && number <= 2000))
        {
            System.out.println("Entered count of pages must be >= 50 and <= 2000.");
            return inputCountOfPages(str, scanner);
        }
        return number;
    }

    public static String inputTypeOfBook(String str, Scanner scanner)
    {
        // Метод для ввода типа книги из консоли. Допустимые варианты paper или electronic.
        System.out.print(str);
        String typeOfBook = scanner.nextLine().toUpperCase();
        for (Book.TypeOfBook temp : Book.TypeOfBook.values())
        {
            if (temp.name().equals(typeOfBook))
            {
                return temp.name();
            }
        }
        System.out.println("Type of book must be paper or electronic.");
        return inputTypeOfBook(str, scanner);
    }

    public static String inputStr(String str, Scanner scanner)
    {
        // Метод для ввода строки из консоли.
        System.out.print(str);
        String text = scanner.nextLine().trim();
        return text;
    }

    public static String inputUserName(String str, Scanner scanner)
    {
        // Метод для ввода имени пользователя из консоли.
        Pattern pattern = Pattern.compile("^(([a-zA-Z0-9]{1,15}(\\s[a-zA-Z0-9]{1,15}))|([a-zA-Z0-9]{1,31}))$");
        System.out.print(str);
        String text = scanner.nextLine();
        Matcher matcher = pattern.matcher(text);
        if (!matcher.find())
        {
            System.out.println("User name is incorrect.");
            System.out.println("User name may be contains letters A-Za-z and digits. A single tab character is allowed. Max length of username 31 symbol.");
            return inputUserName(str, scanner);
        }
        return text;
    }

    public static String inputUserPassword(String str, Scanner scanner)
    {
        // Метод для ввода пароля из консоли.
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{7,20}$");
        System.out.print(str);
        String text = scanner.nextLine();
        Matcher matcher = pattern.matcher(text);
        if (matcher.find())
        {
            return text;
        }
        System.out.println("Password is incorrect.");
        System.out.println("Password may be contains letters A-Za-z, digits and underscores. Min length = 7 symbols, max length = 20 symbols.");
        return inputUserPassword(str, scanner);
    }

    public static String inputEmailAddress(String str, Scanner scanner)
    {
        // Метод для ввода email адреса из консоли.
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_\\-]+(\\.[a-zA-Z0-9_\\-]+)*@[a-zA-Z0-9_\\-]+(\\.[a-zA-Z0-9_\\-]+)*(\\.[a-zA-Z]{2,})$");
        System.out.print(str);
        String text = scanner.nextLine();
        Matcher matcher = pattern.matcher(text);
        if (matcher.find())
        {
            return text;
        }
        System.out.println("Email address is incorrect.");
        return inputEmailAddress(str, scanner);
    }
}