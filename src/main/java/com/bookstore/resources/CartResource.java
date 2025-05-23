package com.bookstore.resources;

import java.util.HashMap;
import java.util.Map;

import com.bookstore.models.Cart;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    private static final Map<Integer, Cart> carts = new HashMap<>();

    @POST
    @Path("/items")
    public Response addToCart(@PathParam("customerId") int customerId, @QueryParam("bookId") int bookId, @QueryParam("quantity") int quantity) {
        carts.computeIfAbsent(customerId, Cart::new).addItem(bookId, quantity);
        return Response.ok().entity("Item added to cart").build();
    }

    @GET
    public Response getCart(@PathParam("customerId") int customerId) {
        Cart cart = carts.get(customerId);
        if (cart == null) {
            throw new WebApplicationException("Cart not found", Response.Status.NOT_FOUND);
        }
        return Response.ok(cart).build();
    }
}
