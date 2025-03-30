package com.bookstore.storage;

import java.util.HashMap;
import java.util.Map;

import com.bookstore.models.Book;
import com.bookstore.models.Customer;

public class DataStore {
    public static final Map<Integer, Book> books = new HashMap<>();

    public static void addCustomer(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

