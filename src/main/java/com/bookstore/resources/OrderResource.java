package com.bookstore.resources;

import java.util.List;

import com.bookstore.exceptions.CustomerNotFoundException;
import com.bookstore.models.Customer;
import com.bookstore.models.Order;
import com.bookstore.storage.DataStore;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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

        // Removed duplicate method definition to resolve the error

        Order order = new Order(customerId, customer.getCart().getItems());
        DataStore.addOrder(order);
        customer.getCart().getItems().clear(); // Correctly clear the cart items after order creation

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