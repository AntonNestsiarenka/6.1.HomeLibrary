package com.company.bean;

import java.util.Arrays;
import java.util.Objects;

public class Book implements Comparable<Book> {

    private String name;
    private String[] authors;
    private int releaseYear;
    private int countOfPages;
    private TypeOfBook typeOfBook;

    public Book(String name, String[] authors, int releaseYear, int countOfPages, TypeOfBook typeOfBook) {
        this.name = name;
        this.authors = authors;
        this.releaseYear = releaseYear;
        this.countOfPages = countOfPages;
        this.typeOfBook = typeOfBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getCountOfPages() {
        return countOfPages;
    }

    public void setCountOfPages(int countOfPages) {
        this.countOfPages = countOfPages;
    }

    public TypeOfBook getTypeOfBook() {
        return typeOfBook;
    }

    public void setTypeOfBook(TypeOfBook typeOfBook) {
        this.typeOfBook = typeOfBook;
    }

    @Override
    public int compareTo(Book o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Book name: " + name + " | Authors: " +
                Arrays.toString(authors) + " | Year: " +
                releaseYear + " | Pages: " +
                countOfPages + " | " +
                typeOfBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return releaseYear == book.releaseYear &&
                countOfPages == book.countOfPages &&
                name.equals(book.name) &&
                Arrays.equals(authors, book.authors) &&
                typeOfBook == book.typeOfBook;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, releaseYear, countOfPages, typeOfBook);
        result = 31 * result + Arrays.hashCode(authors);
        return result;
    }

    public enum TypeOfBook {

        ELECTRONIC("Electronic"),
        PAPER("Paper");

        private String typeOfBook;

        TypeOfBook(String typeOfBook) {
            this.typeOfBook = typeOfBook;
        }

        public String getTypeOfBook() {
            return typeOfBook;
        }

        public void setTypeOfBook(String typeOfBook) {
            this.typeOfBook = typeOfBook;
        }

        @Override
        public String toString() {
            return typeOfBook + " variant";
        }
    }
}