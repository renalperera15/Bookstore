package com.bookstore.models;

import com.bookstore.storage.DataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Order {
    private static int idCounter = 1;

    private int orderId;
    private int customerId;
    private List<Book> books;
    private double totalPrice;

    public Order(int customerId, Map<Integer, Integer> items) {
        this.orderId = idCounter++;
        this.customerId = customerId;
        this.books = new ArrayList<>();
        this.totalPrice = 0;

        for (Map.Entry<Integer, Integer> entry : items.entrySet()) {
            int bookId = entry.getKey();
            int quantity = entry.getValue();

            Book book = DataStore.getBook(bookId);
            if (book != null) {
                for (int i = 0; i < quantity; i++) {
                    books.add(book);
                    totalPrice += book.getPrice();
                }
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
