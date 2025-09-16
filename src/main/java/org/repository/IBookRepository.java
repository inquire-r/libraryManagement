package org.repository;

import java.util.List;
import org.model.Book;

public interface IBookRepository {
    void addBook(Book book);
    void removeBook(String isbn);
    void updateBook(Book book);
    Book getBookByISBN(String isbn);
    List<Book> searchByTitle(String title);
    List<Book> searchByAuthor(String author);
}