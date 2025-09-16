package org.service;

import org.model.Book;
import org.repository.IBookRepository;

import java.util.List;

public class SearchService {

    private final IBookRepository bookRepository;

    public SearchService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.searchByTitle(title);
    }

    public List<Book> searchByAuthor(String author) {
        return bookRepository.searchByAuthor(author);
    }

    public Book searchByISBN(String isbn) {
        return bookRepository.getBookByISBN(isbn);
    }
}