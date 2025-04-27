package com.bookstore.models;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String password; // Simple password for coursework
    private Cart cart; // Cart field for storing items

    // No-argument constructor for Jackson
    public Customer() {
        this.cart = new Cart();  // Initialize cart here
    }

    // Parameterized constructor (if you need it for other use cases)
    public Customer(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.cart = new Cart();  // Initialize cart here
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for Cart, now it will return the initialized Cart
    public Cart getCart() {
        return cart;
    }

    // Setter for Cart (if you need to set the cart explicitly)
    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
