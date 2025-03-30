package com.bookstore.resources;

import com.bookstore.models.Customer;
import com.bookstore.storage.DataStore;
import com.bookstore.exceptions.CustomerNotFoundException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @POST
    public Response addCustomer(Customer customer) {
        DataStore.addCustomer(customer);
        return Response.status(Response.Status.CREATED).entity(customer).build();
    }

    @GET
    public Response getAllCustomers() {
        List<Customer> customers = DataStore.getAllCustomers();
        return Response.ok(customers).build();
    }

    @GET
    @Path("/{id}")
    public Response getCustomer(@PathParam("id") int id) throws CustomerNotFoundException {
        Customer customer = DataStore.getCustomer(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found with ID: " + id);
        }
        return Response.ok(customer).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") int id, Customer updatedCustomer) throws CustomerNotFoundException {
        Customer customer = DataStore.getCustomer(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found with ID: " + id);
        }
        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        return Response.ok(customer).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) throws CustomerNotFoundException {
        boolean removed = DataStore.removeCustomer(id);
        if (!removed) {
            throw new CustomerNotFoundException("Customer not found with ID: " + id);
        }
        return Response.noContent().build();
    }
}