package org.repository;

import org.model.Book;
import org.utils.LoggerUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepository implements IBookRepository {

    private final Map<String, Book> books = new HashMap<>();

    @Override
    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
        LoggerUtil.logInfo("Book added: " + book);
    }

    @Override
    public void removeBook(String isbn) {
        Book removed = books.remove(isbn);
        if (removed != null) {
            LoggerUtil.logInfo("Book removed: " + removed);
        } else {
            LoggerUtil.logInfo("Attempted to remove book, but ISBN not found: " + isbn);
        }
    }

    @Override
    public void updateBook(Book book) {
        books.put(book.getIsbn(), book);
        LoggerUtil.logInfo("Book updated: " + book);
    }

    @Override
    public Book getBookByISBN(String isbn) {
        return books.get(isbn);
    }

    @Override
    public List<Book> searchByTitle(String title) {
        List<Book> results = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    @Override
    public List<Book> searchByAuthor(String author) {
        List<Book> results = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }
}