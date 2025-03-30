package com.bookstore.storage;

import com.bookstore.models.Book;
import java.util.HashMap;
import java.util.Map;

public class DataStore {
    public static final Map<Integer, Book> books = new HashMap<>();
}

