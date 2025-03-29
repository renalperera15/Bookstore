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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
