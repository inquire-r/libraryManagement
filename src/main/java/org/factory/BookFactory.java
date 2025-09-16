package org.factory;

import org.model.Book;
import org.model.BookStatus;

public class BookFactory {

    public static Book createBook(String title, String author, String isbn, int publicationYear) {
        return new Book(title, author, isbn, publicationYear, BookStatus.AVAILABLE);
    }
}
