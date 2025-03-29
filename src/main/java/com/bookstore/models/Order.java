package com.bookstore.models;

import java.util.List;

public class Order {
    private int orderId;
    private int customerId;
    private List<Book> books;
    private double totalPrice;

    public Order(int orderId, int customerId, List<Book> books, double totalPrice) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.books = books;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
}
