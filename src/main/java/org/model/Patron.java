package org.model;

import java.util.ArrayList;
import java.util.List;

public class Patron {
    private String id;
    private String name;
    private String contact;
    private List<String> borrowedBooks;

    public Patron(String id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<String> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public void borrowBook(String isbn) {
        borrowedBooks.add(isbn);
    }

    public void returnBook(String isbn) {
        borrowedBooks.remove(isbn);
    }

    @Override
    public String toString() {
        return "Patron { " +
                "ID='" + id + '\'' +
                ", Name='" + name + '\'' +
                ", Contact='" + contact + '\'' +
                ", BorrowedBooks=" + borrowedBooks +
                " }";
    }
}
