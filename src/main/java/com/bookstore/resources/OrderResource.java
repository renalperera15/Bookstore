package com.bookstore.resources;

import com.bookstore.models.Order;
import com.bookstore.models.Customer;
import com.bookstore.storage.DataStore;
import com.bookstore.exceptions.CustomerNotFoundException;
import com.bookstore.exceptions.CartNotFoundException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @POST
    public Response createOrder(@PathParam("customerId") int customerId) throws CustomerNotFoundException {
        Customer customer = DataStore.getCustomer(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
        }

        if (customer.getCart().getItems().isEmpty()) {
            throw new CartNotFoundException("Cart is empty, cannot create order.");
        }

        Order order = new Order(customerId, customer.getCart().getItems());
        DataStore.addOrder(order);
        customer.getCart().clear(); // Empty the cart after order creation

        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @GET
    public Response getOrders(@PathParam("customerId") int customerId) {
        Customer customer = DataStore.getCustomer(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
        }

        List<Order> orders = DataStore.getOrdersByCustomer(customerId);
        return Response.ok(orders).build();
    }

    @GET
    @Path("/{orderId}")
    public Response getOrder(@PathParam("customerId") int customerId, @PathParam("orderId") int orderId) {
        Order order = DataStore.getOrder(orderId);
        if (order == null || order.getCustomerId() != customerId) {
            return Response.status(Response.Status.NOT_FOUND).entity("Order not found.").build();
        }
        return Response.ok(order).build();
    }
}