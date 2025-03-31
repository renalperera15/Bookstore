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

    /**
     * Returns the items in the cart.
     * @return A map of book IDs to their quantities.
     */
    public Map<Integer, Integer> getItems() {
        return items;
    }

    /**
     * Clears all items from the cart.
     */
    public void clear() {
        items.clear();
    }

    // Getters and Setters

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
}