package com.bookstore.storage;

import com.bookstore.models.*;

import java.util.*;
import java.util.stream.Collectors;

public class DataStore {
    private static final List<Author> authors = new ArrayList<>();
    private static final List<Book> books = new ArrayList<>();
    private static final List<Customer> customers = new ArrayList<>();
    private static final List<Order> orders = new ArrayList<>();

    // ==== Authors ====
    public static void addAuthor(Author author) {
        authors.add(author);
    }

    public static List<Author> getAllAuthors() {
        return new ArrayList<>(authors);
    }

    public static Author getAuthor(int id) {
        return authors.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    public static boolean removeAuthor(int id) {
        Author author = getAuthor(id);
        if (author != null) {
            books.removeIf(book -> book.getAuthorId() == id);
            authors.remove(author);
            return true;
        }
        return false;
    }

    public static List<Book> getBooksByAuthor(int authorId) {
        return books.stream().filter(b -> b.getAuthorId() == authorId).collect(Collectors.toList());
    }

    // ==== Books ====
    public static void addBook(Book book) {
        books.add(book);
    }

    public static Book getBook(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    // ==== Customers ====
    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public static List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    public static Customer getCustomer(int id) {
        return customers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public static boolean removeCustomer(int id) {
        Customer customer = getCustomer(id);
        if (customer != null) {
            customers.remove(customer);
            return true;
        }
        return false;
    }

    // ==== Orders ====
    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static List<Order> getOrdersByCustomer(int customerId) {
        return orders.stream().filter(o -> o.getCustomerId() == customerId).collect(Collectors.toList());
    }

    public static Order getOrder(int orderId) {
        return orders.stream().filter(o -> o.getOrderId() == orderId).findFirst().orElse(null);
    }
}
