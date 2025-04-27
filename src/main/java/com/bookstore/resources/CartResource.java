package com.bookstore.resources;

import com.bookstore.models.Cart;
import com.bookstore.models.Customer;
import com.bookstore.storage.DataStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    // Add an item to the cart for the customer
    @POST
    @Path("/items")
    public Response addToCart(@PathParam("customerId") int customerId, CartItem cartItem) {
        try {
            // Retrieve the customer from the DataStore
            Customer customer = DataStore.getCustomer(customerId);
            if (customer == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Customer not found with ID: " + customerId)
                        .build();
            }

            // Check if the customer has a cart; if not, create one
            if (customer.getCart() == null) {
                customer.setCart(new Cart(customerId));
            }

            // Add the item to the cart
            customer.getCart().addItem(cartItem.getBookId(), cartItem.getQuantity());

            return Response.status(Response.Status.CREATED)
                    .entity("Item added to cart successfully")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while adding item to cart.")
                    .build();
        }
    }

    // Get the cart for the customer
    @GET
    public Response getCart(@PathParam("customerId") int customerId) {
        try {
            // Retrieve the customer from the DataStore
            Customer customer = DataStore.getCustomer(customerId);
            if (customer == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Customer not found with ID: " + customerId)
                        .build();
            }

            // If the customer doesn't have a cart, return an empty cart
            Cart cart = customer.getCart();
            if (cart == null) {
                cart = new Cart(customerId); // Empty cart
            }

            return Response.ok(cart).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while fetching the cart.")
                    .build();
        }
    }

    // Helper class to represent cart items in the request body
    public static class CartItem {
        private int bookId;
        private int quantity;

        // Getters and setters
        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
