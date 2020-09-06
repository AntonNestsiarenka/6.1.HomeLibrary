package com.company.bean;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CatalogOfBooks {

    public static final String FILE_NAME_OF_CATALOG = "books_data.txt";

    private static CatalogOfBooks catalogOfBooks;

    private final ArrayList<Book> books;

    public static CatalogOfBooks createInstance() {
        if (catalogOfBooks == null)
            catalogOfBooks = new CatalogOfBooks();
        return catalogOfBooks;
    }

    private CatalogOfBooks()
    {
        books = new ArrayList<>();
    }

    public static CatalogOfBooks getCatalogOfBooks() {
        return catalogOfBooks;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book)
    {
        books.add(book);
    }

    public void removeBook(Book book)
    {
        books.remove(book);
    }

    public void clearBooks()
    {
        books.clear();
    }

    public void catalogInit() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME_OF_CATALOG)));
        String currentLine;
        Pattern pattern = Pattern.compile("^([а-яА-Яa-zA-Z\".,\\s-]+) \\| (([а-яА-Яa-zA-Z-\\s.]+)(,\\s[а-яА-Яa-zA-Z-\\s.]+)*) \\| (\\d+) \\| (\\d+) \\| (\\w+)$");
        while ((currentLine = bufferedReader.readLine()) != null) {
            Matcher matcher = pattern.matcher(currentLine);
            if (matcher.find()) {
                String[] authors = matcher.group(2).split(", ");
                books.add(new Book(matcher.group(1), authors, Integer.valueOf(matcher.group(5)), Integer.valueOf(matcher.group(6)), Book.TypeOfBook.valueOf(matcher.group(7).toUpperCase())));
            }
        }
        bufferedReader.close();
    }

    @Override
    public String toString() {
        return "CatalogOfBooks{" +
                "books=" + books +
                '}';
    }
}