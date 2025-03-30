package com.bookstore.models;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private int customerId;
    private final Map<Integer, Integer> items = new HashMap<>(); // Book ID -> Quantity

    public Cart(int customerId) {
        this.customerId = customerId;
    }

    public void addItem(int bookId, int quantity) {
        items.put(bookId, items.getOrDefault(bookId, 0) + quantity);
    }

    public void removeItem(int bookId) {
        items.remove(bookId);
    }

    // Getters and Setters

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
