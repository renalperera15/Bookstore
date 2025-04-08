package com.bookstore.exceptions;

public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException() {
        super("Cart not found for the specified customer.");
    }

    public CartNotFoundException(String message) {
        super(message);
    }
}
