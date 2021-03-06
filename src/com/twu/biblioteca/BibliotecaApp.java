package com.twu.biblioteca;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BibliotecaApp {
    private Book[] books = {new Book("Book1", "Author1", 2016),
            new Book("Book2", "Author2", 2016),
            new Book("Book3", "Author3", 2017)};

    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.printWelcome();
        bibliotecaApp.printMainMenu();
    }

    public void printWelcome() {
        System.out.println("Welcome to Biblioteca Public Library!");
    }

    public void listBook() {
        for (Book book : books) {
            if (book.getStatus() == Book.Status.available) System.out.println(book.getName());
        }
    }

    public void printBookDetails() {
        String BOOK_COLUMN = "Title\tAuthor\tYear\n" +
                "--------------------";
        System.out.println(BOOK_COLUMN);
        for (Book book : books) {
            System.out.printf("%s\t%s\t%s\n", book.getName(), book.getAuthor(), book.getYear());
        }
    }

    public void printMainMenu() {
        String MAIN_MENU_HEAD = "---------------\n" + "" +
                "Menu\n"
                + "---------------";
        String[] MAIN_MENU_ITEM = {"1. List Books", "2. Checkout Book", "3. Return Book", "4. Quit"};
        String MAIN_MENU_TIP = String.format("---------------\nPlease select your option (1-%d):", MAIN_MENU_ITEM.length);
        System.out.println(MAIN_MENU_HEAD);
        for (String item : MAIN_MENU_ITEM) {
            System.out.println(item);
        }
        System.out.println(MAIN_MENU_TIP);

        menuChoiceHandler();
    }

    private void menuChoiceHandler() {
        Scanner input = new Scanner(System.in);
        boolean toQuit = false;
        try {
            while (!toQuit) {
                Integer userChoice = input.nextInt();
                switch (userChoice) {
                    case 1:
                        this.listBook();
                        continue;
                    case 2:
                        this.checkoutBook(input.next());
                        break;
                    case 3:
                        this.returnBook(input.next());
                        break;
                    case 4:
                        System.out.println("Bye Bye!");
                        toQuit = true;
                        break;
                    default:
                        System.out.println("Select a valid option!");
                }
            }
        } catch (NoSuchElementException ex) {

        }
    }

    public void checkoutBook(String name) {
        String BOOK_CHECKOUT_SUCCESS = "Thank you! Enjoy the book.";
        String BOOK_CHECKOUT_UNSUCCESS = "That book is not available.";

        Book book = findBookByName(name);
        if (book == null || book.getStatus() == Book.Status.checkedout) {
            System.out.println(BOOK_CHECKOUT_UNSUCCESS);
        } else {
            book.setStatus(Book.Status.checkedout);
            System.out.println(BOOK_CHECKOUT_SUCCESS);
        }
    }

    public void returnBook(String name) {
        String BOOK_RETURN_SUCCESS = "Thank you for returning the book.";
        String BOOK_RETURN_UNSUCCESS = "That is not a valid book to return.";

        Book book = findBookByName(name);
        if (book == null || book.getStatus() == Book.Status.available)
            System.out.println(BOOK_RETURN_UNSUCCESS);
        else {
            book.setStatus(Book.Status.available);
            System.out.println(BOOK_RETURN_SUCCESS);
        }
    }

    private Book findBookByName(String name) {
        try {
            return books[Arrays.asList(books).indexOf(new Book(name))];
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
}
