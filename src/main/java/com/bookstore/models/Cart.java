package com.bookstore.models;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private int customerId;  // Customer ID to associate the cart with a customer
    private final Map<Integer, Integer> items = new HashMap<>(); // Book ID -> Quantity

    // No-argument constructor for initialization without customerId
    public Cart() {
    }

    // Constructor to initialize the cart with a customerId
    public Cart(int customerId) {
        this.customerId = customerId;
    }

    // Add item to the cart: Book ID and Quantity
    public void addItem(int bookId, int quantity) {
        items.put(bookId, items.getOrDefault(bookId, 0) + quantity);
    }

    // Remove item from the cart by Book ID
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

    // Getter and Setter for customerId
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
