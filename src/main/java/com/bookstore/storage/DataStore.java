package com.bookstore.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bookstore.models.Author;
import com.bookstore.models.Book;
import com.bookstore.models.Customer;
import com.bookstore.models.Order;

public class DataStore {
    private static final List<Author> authors = new ArrayList<>();
    private static final List<Book> books = new ArrayList<>();

    public static void addAuthor(Author author) {
        authors.add(author);
    }

    public static List<Author> getAllAuthors() {
        return new ArrayList<>(authors);
    }

    public static Author getAuthor(int id) {
        return authors.stream()
                .filter(author -> author.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public static boolean removeAuthor(int id) {
        Author author = getAuthor(id);
        if (author != null) {
            // Remove all books by this author
            books.removeIf(book -> book.getAuthorId() == id);
            // Remove the author
            authors.remove(author);
            return true;
        }
        return false;
    }

    public static List<Book> getBooksByAuthor(int authorId) {
        return books.stream()
                .filter(book -> book.getAuthorId() == authorId)
                .collect(Collectors.toList());
    }

    public static void addBook(Book book) {
        books.add(book);
    }

    public static boolean removeCustomer(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static Customer getCustomer(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static List<Customer> getAllCustomers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void addCustomer(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void addOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static List<Order> getOrdersByCustomer(int customerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static Order getOrder(int orderId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}