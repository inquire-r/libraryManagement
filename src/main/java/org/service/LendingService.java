package org.service;

import org.model.Book;
import org.model.BookStatus;
import org.model.Patron;
import org.observer.EventManager;
import org.repository.IBookRepository;
import org.repository.IPatronRepository;
import org.utils.LoggerUtil;
public class LendingService implements ILendingService {

    private final IBookRepository bookRepository;
    private final IPatronRepository patronRepository;
    private final EventManager eventManager;

    public LendingService(IBookRepository bookRepository, IPatronRepository patronRepository, EventManager eventManager) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.eventManager = eventManager;
    }

    @Override
    public boolean checkoutBook(String isbn, String patronId) {
        Book book = bookRepository.getBookByISBN(isbn);
        Patron patron = patronRepository.getPatronById(patronId);

        if (book == null) {
            LoggerUtil.logError("Checkout failed: Book with ISBN " + isbn + " not found.", null);
            return false;
        }

        if (patron == null) {
            LoggerUtil.logError("Checkout failed: Patron with ID " + patronId + " not found.", null);
            return false;
        }

        if (book.getStatus() == BookStatus.BORROWED) {
            LoggerUtil.logInfo("Checkout failed: Book is already borrowed.");
            return false;
        }

        book.setStatus(BookStatus.BORROWED);
        patron.borrowBook(isbn);

        bookRepository.updateBook(book);
        patronRepository.updatePatron(patron);

        LoggerUtil.logInfo("Checkout successful: Book " + book.getTitle() + " checked out by " + patron.getName());
        eventManager.notifyObservers("Book '" + book.getTitle() + "' has been checked out by " + patron.getName());
        return true;
    }

    @Override
    public boolean returnBook(String isbn, String patronId) {
        Book book = bookRepository.getBookByISBN(isbn);
        Patron patron = patronRepository.getPatronById(patronId);

        if (book == null) {
            LoggerUtil.logError("Return failed: Book with ISBN " + isbn + " not found.", null);
            return false;
        }

        if (patron == null) {
            LoggerUtil.logError("Return failed: Patron with ID " + patronId + " not found.", null);
            return false;
        }

        if (!patron.getBorrowedBooks().contains(isbn)) {
            LoggerUtil.logInfo("Return failed: Patron did not borrow this book.");
            return false;
        }

        book.setStatus(BookStatus.AVAILABLE);
        patron.returnBook(isbn);

        bookRepository.updateBook(book);
        patronRepository.updatePatron(patron);

        LoggerUtil.logInfo("Return successful: Book " + book.getTitle() + " returned by " + patron.getName());
        eventManager.notifyObservers("Book '" + book.getTitle() + "' has been returned by " + patron.getName());
        return true;
    }
}