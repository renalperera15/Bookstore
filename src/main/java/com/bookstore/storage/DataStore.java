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
    private static final List<Customer> customers = new ArrayList<>();
    private static final List<Order> orders = new ArrayList<>();

    private static int authorIdCounter = 1;
    private static int bookIdCounter = 1;
    private static int customerIdCounter = 1;
    private static int orderIdCounter = 1;

    // Author Management
    public static void addAuthor(Author author) {
        author.setId(authorIdCounter++);
        authors.add(author);
    }

    public static Author getAuthor(int id) {
        return authors.stream().filter(author -> author.getId() == id).findFirst().orElse(null);
    }

    public static List<Author> getAllAuthors() {
        return authors;
    }

    public static boolean removeAuthor(int id) {
        return authors.removeIf(author -> author.getId() == id);
    }

    public static List<Book> getBooksByAuthor(int authorId) {
        return books.stream().filter(book -> book.getAuthorId() == authorId).collect(Collectors.toList());
    }

    // Book Management
    public static void addBook(Book book) {
        book.setId(bookIdCounter++);
        books.add(book);
    }

    public static Book getBook(int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }

    public static List<Book> getAllBooks() {
        return books;
    }

    // Customer Management
    public static void addCustomer(Customer customer) {
        customer.setId(customerIdCounter++);
        customers.add(customer);
    }

    public static Customer getCustomer(int id) {
        return customers.stream().filter(customer -> customer.getId() == id).findFirst().orElse(null);
    }

    public static List<Customer> getAllCustomers() {
        return customers;
    }

    public static boolean removeCustomer(int id) {
        return customers.removeIf(customer -> customer.getId() == id);
    }

    // Order Management
    public static void addOrder(Order order) {
        order.setOrderId(orderIdCounter++);
        orders.add(order);
    }

    public static Order getOrder(int orderId) {
        return orders.stream().filter(order -> order.getOrderId() == orderId).findFirst().orElse(null);
    }

    public static List<Order> getOrdersByCustomer(int customerId) {
        return orders.stream().filter(order -> order.getCustomerId() == customerId).collect(Collectors.toList());
    }
}
