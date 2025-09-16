package org.menu;

import org.factory.BookFactory;
import org.model.Book;
import org.model.Patron;
import org.observer.EventManager;
import org.observer.NotificationService;
import org.repository.BookRepository;
import org.repository.IBookRepository;
import org.repository.IPatronRepository;
import org.repository.PatronRepository;
import org.service.ILendingService;
import org.service.LendingService;
import org.service.SearchService;

import java.util.List;
import java.util.Scanner;

public class LibraryMenu {

    private final IBookRepository bookRepository;
    private final IPatronRepository patronRepository;
    private final ILendingService lendingService;
    private final SearchService searchService;
    private final EventManager eventManager;
    private final Scanner scanner;

    public LibraryMenu() {
        this.bookRepository = new BookRepository();
        this.patronRepository = new PatronRepository();
        this.eventManager = new EventManager();
        this.lendingService = new LendingService(bookRepository, patronRepository, eventManager);
        this.searchService = new SearchService(bookRepository);
        this.scanner = new Scanner(System.in);

        // Registering observers
        this.eventManager.registerObserver(new NotificationService("Admin"));
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add Patron");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Search Book by Author");
            System.out.println("5. Search Book by ISBN");
            System.out.println("6. Checkout Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addBook();
                case 2 -> addPatron();
                case 3 -> searchByTitle();
                case 4 -> searchByAuthor();
                case 5 -> searchByISBN();
                case 6 -> checkoutBook();
                case 7 -> returnBook();
                case 8 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addBook() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter publication year: ");
        int year = Integer.parseInt(scanner.nextLine());

        Book book = BookFactory.createBook(title, author, isbn, year);
        bookRepository.addBook(book);
        System.out.println("Book added successfully.");
    }

    private void addPatron() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter contact: ");
        String contact = scanner.nextLine();

        Patron patron = new Patron(id, name, contact);
        patronRepository.addPatron(patron);
        System.out.println("Patron added successfully.");
    }

    private void searchByTitle() {
        System.out.print("Enter title to search: ");
        String title = scanner.nextLine();

        List<Book> books = searchService.searchByTitle(title);
        if (books.isEmpty()) {
            System.out.println("No books found with that title.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void searchByAuthor() {
        System.out.print("Enter author to search: ");
        String author = scanner.nextLine();

        List<Book> books = searchService.searchByAuthor(author);
        if (books.isEmpty()) {
            System.out.println("No books found with that author.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void searchByISBN() {
        System.out.print("Enter ISBN to search: ");
        String isbn = scanner.nextLine();

        Book book = searchService.searchByISBN(isbn);
        if (book == null) {
            System.out.println("No book found with that ISBN.");
        } else {
            System.out.println(book);
        }
    }

    private void checkoutBook() {
        System.out.print("Enter ISBN of book: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter Patron ID: ");
        String patronId = scanner.nextLine();

        if (lendingService.checkoutBook(isbn, patronId)) {
            System.out.println("Checkout successful.");
        } else {
            System.out.println("Checkout failed.");
        }
    }

    private void returnBook() {
        System.out.print("Enter ISBN of book: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter Patron ID: ");
        String patronId = scanner.nextLine();

        if (lendingService.returnBook(isbn, patronId)) {
            System.out.println("Return successful.");
        } else {
            System.out.println("Return failed.");
        }
    }
}



