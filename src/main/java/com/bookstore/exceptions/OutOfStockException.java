package com.bookstore.exceptions;

public class OutOfStockException extends RuntimeException {
    
    public OutOfStockException() {
        super("The requested book is out of stock.");
    }

    public OutOfStockException(String message) {
        super(message);
    }
}
