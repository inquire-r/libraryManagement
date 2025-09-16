package org.service;

public interface ILendingService {
    boolean checkoutBook(String isbn, String patronId);
    boolean returnBook(String isbn, String patronId);
}
