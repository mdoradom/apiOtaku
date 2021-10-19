package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

class Book {
    public String title;

    public Book(String title) {
        this.title = title;
    }
}

class Author {
    public String name;
    public String bDate;
    public ArrayList<Book> bookList;

    public Author(String name, String bDate) {
        this.name = name;
        this.bDate = bDate;
    }
}

class Library {
    public String name;
    public String city;
    public ArrayList<Book> bookList = new ArrayList<>();

    public Library(String name, String city) {
        this.name = name;
        this.city = city;
    }
}

public class Ejercicio2 {

    static Path file = Paths.get("library.json");
    static ObjectMapper objectMapper = new ObjectMapper();

    public static void main (String[] args) {
        // Obtenir la llista de llibres de cada autor
        Book book1 = new Book("book1");
        Book book2 = new Book("book2");
        Book book3 = new Book("book3");

        Author author1 = new Author("Authro1", "1-1-2000");

        author1.bookList.add(book1);
        author1.bookList.add(book2);
        author1.bookList.add(book3);

        // Obtenir la llista de llibres de cada biblioteca

        Library library1 = new Library("Library1", "City1");

        library1.bookList.add(book1);
        library1.bookList.add(book2);
        library1.bookList.add(book3);
    }

}
