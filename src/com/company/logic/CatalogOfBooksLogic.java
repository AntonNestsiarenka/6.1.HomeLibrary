package com.company.logic;

import com.company.bean.Book;
import com.company.bean.CatalogOfBooks;
import com.company.view.Viewer;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CatalogOfBooksLogic {

    private Viewer viewer;

    public CatalogOfBooksLogic()
    {
        viewer = new Viewer();
    }

    public CatalogOfBooksLogic(Viewer viewer)
    {
        this.viewer = viewer;
    }

    public void showBookCatalog(CatalogOfBooks catalogOfBooks)
    {
        // Выводит в консоль весь каталог доступных книг.
        viewer.printMessage("Book catalog:");
        int i = 1;
        for (Book book : catalogOfBooks.getBooks())
        {
            viewer.printMessage(i++ + ". " + book.toString());
        }
    }

    public void showBooks(ArrayList<Book> books)
    {
        int i = 1;
        for (Book book : books)
        {
            viewer.printMessage(i++ + ". " + book.toString());
        }
    }

    public ArrayList<Book> findBooksByName(CatalogOfBooks catalogOfBooks, String bookName)
    {
        // Ищет книги по имени в каталоге всех доступных книг.
        ArrayList<Book> books = new ArrayList<>();
        for (Book book : catalogOfBooks.getBooks())
        {
            if (book.getName().equalsIgnoreCase(bookName))
                books.add(book);
        }
        return books;
    }

    public ArrayList<Book> findByAuthor(CatalogOfBooks catalogOfBooks, String authorName)
    {
        // Ищет книги по имени автора в каталоге всех доступных книг.
        ArrayList<Book> books = new ArrayList<>();
        for (Book book : catalogOfBooks.getBooks())
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
        return books;
    }

    public void addBookToCatalog(CatalogOfBooks catalogOfBooks, Book book) throws IOException
    {
        // Добавляет книгу в каталог книг.
        if (!catalogOfBooks.getBooks().contains(book))
        {
            FileWriter fileWriter = new FileWriter(CatalogOfBooks.FILE_NAME_OF_CATALOG, true);
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
            fileWriter.write("\n" + finalBookData);
            fileWriter.flush();
            fileWriter.close();
            catalogOfBooks.addBook(book);
        }
        else {
            viewer.printMessage("Such a book already exists in the catalog.");
        }
    }

    public void removeBookFromCatalog(CatalogOfBooks catalogOfBooks, Book book) throws IOException
    {
        // Удаляет книгу из каталога книг.
        catalogOfBooks.removeBook(book);
        FileWriter fileWriter = new FileWriter(CatalogOfBooks.FILE_NAME_OF_CATALOG);
        StringBuilder bookData = new StringBuilder();
        for (Book bookObj : catalogOfBooks.getBooks()) {
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
        fileWriter.write(bookData.toString());
        fileWriter.flush();
        fileWriter.close();
    }
}