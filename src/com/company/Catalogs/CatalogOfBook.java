package com.company.Catalogs;

import com.company.Book.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CatalogOfBook {

    public static final String FILE_NAME_OF_CATALOG = "catalog.txt";

    private static CatalogOfBook catalogOfBook;

    private ArrayList<Book> books;

    public static CatalogOfBook createInstance() {
        if (catalogOfBook == null)
            catalogOfBook = new CatalogOfBook();
        return catalogOfBook;
    }

    private CatalogOfBook()
    {
        books = new ArrayList<>();
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
                Pattern pattern = Pattern.compile("^(.+) \\| (.+) \\| (.+) \\| (.+) \\| (.+)$");
                Matcher matcher = pattern.matcher(temp);
                while (matcher.find()) {
                    String[] authors = matcher.group(2).split(", ");
                    books.add(new Book(matcher.group(1), authors, Integer.valueOf(matcher.group(3)), Integer.valueOf(matcher.group(4)), Book.TypeOfBook.valueOf(matcher.group(5).toUpperCase())));
                }
            }
            else break;
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        books.sort(Book::compareTo);
    }

    public static CatalogOfBook getCatalogOfBook() {
        return catalogOfBook;
    }

    public boolean add(Book book) {
        if (!books.contains(book))
        {
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(FILE_NAME_OF_CATALOG, true);
            } catch (IOException e) {
                System.out.println("Error adding book.");
                System.out.println(e.getMessage());
                return false;
            }
            StringBuilder bookData = new StringBuilder();
            bookData.append(book.getName() + " | ");
            String[] authors = book.getAuthors();
            for (int i = 0; i < authors.length - 1; i++)
            {
                bookData.append(authors[i] + ", ");
            }
            bookData.append(authors[authors.length - 1] + " | ");
            bookData.append(book.getReleaseYear() + " | ");
            bookData.append(book.getCountOfPages() + " | ");
            bookData.append(book.getTypeOfBook().name().toLowerCase());
            String finalBookData = bookData.toString();
            try {
                fileWriter.write("\n" + finalBookData);
                fileWriter.flush();
                fileWriter.close();
            }
            catch (IOException e)
            {
                System.out.println("Error adding book.");
                System.out.println(e.getMessage());
                return false;
            }
            return books.add(book);
        }
        System.out.println("Such a book already exists in the catalog.");
        return false;
    }

    public boolean remove(Book book)
    {
        books.remove(book);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(FILE_NAME_OF_CATALOG);
        } catch (IOException e) {
            System.out.println("Error removing of book.");
            System.out.println(e.getMessage());
            return false;
        }
        StringBuilder bookData = new StringBuilder();
        for (Book bookObj : books) {
            bookData.append(bookObj.getName() + " | ");
            String[] authors = bookObj.getAuthors();
            for (int i = 0; i < authors.length - 1; i++) {
                bookData.append(authors[i] + ", ");
            }
            bookData.append(authors[authors.length - 1] + " | ");
            bookData.append(bookObj.getReleaseYear() + " | ");
            bookData.append(bookObj.getCountOfPages() + " | ");
            bookData.append(bookObj.getTypeOfBook().name().toLowerCase() + "\n");
        }
        try {
            fileWriter.write(bookData.toString());
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("Error removing of book.");
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void showCatalog()
    {
        System.out.println("Book catalog:");
        int i = 1;
        for (Book book : books)
        {
            System.out.println(i++ + ". " + book.toString());
        }
    }

    public ArrayList<Book> findByName(String bookName)
    {
        ArrayList<Book> books = new ArrayList<>();
        for (Book book : this.books)
        {
            if (book.getName().equalsIgnoreCase(bookName))
                books.add(book);
        }
        books.trimToSize();
        return books;
    }

    public ArrayList<Book> findByAuthor(String authorName)
    {
        ArrayList<Book> books = new ArrayList<>();
        for (Book book : this.books)
        {
            for (String name : book.getAuthors())
            {
                if (authorName.equalsIgnoreCase(name))
                {
                    books.add(book);
                    break;
                }
            }
        }
        books.trimToSize();
        return books;
    }

    public static void showBooks(ArrayList<Book> books)
    {
        int i = 1;
        for (Book book : books)
        {
            System.out.println(i++ + ". " + book.toString());
        }
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}