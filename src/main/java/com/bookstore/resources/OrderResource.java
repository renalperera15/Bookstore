package com.bookstore.resources;

import java.util.ArrayList;
import java.util.List;

import com.bookstore.exceptions.CustomerNotFoundException;
import com.bookstore.models.Customer;
import com.bookstore.models.Order;
import com.bookstore.storage.DataStore;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @POST
    public Response createOrder(@PathParam("customerId") int customerId) {
        try {
            // Debug: Log customerId and cart content
            System.out.println("Creating order for customer with ID: " + customerId);

            Customer customer = DataStore.getCustomer(customerId);
            if (customer == null) {
                System.err.println("Customer not found: " + customerId); // Log error
                throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
            }

            // Log the cart items
            System.out.println("Customer Cart Items: " + customer.getCart().getItems());

            if (customer.getCart().getItems().isEmpty()) {
                System.err.println("Customer's cart is empty."); // Log error
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Customer cart is empty. Cannot create order.")
                        .build();
            }

            // Create an order and add it to the data store
            Order order = new Order(customerId, customer.getCart().getItems());
            DataStore.addOrder(order);

            // Log the created order
            System.out.println("Order Created: " + order.getOrderId());

            // Clear the cart after order creation
            customer.getCart().getItems().clear();

            return Response.status(Response.Status.CREATED).entity(order).build();
        } catch (CustomerNotFoundException e) {
            // Log the exception and return a detailed error message
            System.err.println("Customer not found exception: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            // Log general exception for debugging
            System.err.println("Error creating order: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for further debugging

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while creating the order.")
                    .build();
        }
    }

    @GET
    public Response getOrders(@PathParam("customerId") int customerId) {
        try {
            Customer customer = DataStore.getCustomer(customerId);
            if (customer == null) {
                throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
            }

            // Get all orders for the customer
            List<Order> orders = DataStore.getOrdersByCustomer(customerId);
            if (orders == null) {
                orders = new ArrayList<>();
            }

            return Response.ok(orders).build();
        } catch (CustomerNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while fetching orders.")
                    .build();
        }
    }

    @GET
    @Path("/{orderId}")
    public Response getOrder(@PathParam("customerId") int customerId, @PathParam("orderId") int orderId) {
        try {
            // Get the order by its ID
            Order order = DataStore.getOrder(orderId);
            if (order == null || order.getCustomerId() != customerId) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Order not found or does not belong to this customer.")
                        .build();
            }
            return Response.ok(order).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while fetching the order.")
                    .build();
        }
    }
}
