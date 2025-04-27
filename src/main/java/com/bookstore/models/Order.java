package com.bookstore.models;

import com.bookstore.storage.DataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Order {
    private static int idCounter = 1; // Static counter for generating unique order IDs

    private int orderId;
    private int customerId;
    private List<Book> books; // List of books in the order
    private double totalPrice; // Total price of the order

    /**
     * Constructor to create an Order from a customer's cart items.
     *
     * @param customerId The customer who placed the order.
     * @param items A map of bookId and quantity for the items in the cart.
     */
    public Order(int customerId, Map<Integer, Integer> items) {
        this.orderId = idCounter++; // Increment order ID for each new order
        this.customerId = customerId;
        this.books = new ArrayList<>();
        this.totalPrice = 0;

        // Process each book and quantity in the cart
        for (Map.Entry<Integer, Integer> entry : items.entrySet()) {
            int bookId = entry.getKey(); // Get the book ID
            int quantity = entry.getValue(); // Get the quantity of the book

            Book book = DataStore.getBook(bookId); // Retrieve the book from the data store
            if (book != null) {
                // Add the book multiple times based on its quantity
                for (int i = 0; i < quantity; i++) {
                    books.add(book);
                    totalPrice += book.getPrice(); // Add the price of the book to the total price
                }
            } else {
                // Handle case where the book doesn't exist in the store
                System.out.println("Book with ID " + bookId + " not found in DataStore.");
            }
        }
    }

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}
