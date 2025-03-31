package com.bookstore.resources;

import com.bookstore.models.Author;
import com.bookstore.models.Book;
import com.bookstore.storage.DataStore;
import com.bookstore.exceptions.AuthorNotFoundException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    @POST
    public Response addAuthor(Author author) {
        DataStore.addAuthor(author);
        return Response.status(Response.Status.CREATED).entity(author).build();
    }

    @GET
    public Response getAllAuthors() {
        List<Author> authors = DataStore.getAllAuthors();
        return Response.ok(authors).build();
    }

    @GET
    @Path("/{id}")
    public Response getAuthor(@PathParam("id") int id) {
        Author author = DataStore.getAuthor(id);
        if (author == null) {
            throw new AuthorNotFoundException("Author not found with ID: " + id);
        }
        return Response.ok(author).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAuthor(@PathParam("id") int id, Author updatedAuthor) {
        Author author = DataStore.getAuthor(id);
        if (author == null) {
            throw new AuthorNotFoundException("Author not found with ID: " + id);
        }

        author.setName(updatedAuthor.getName());
        author.setBiography(updatedAuthor.getBiography());
        return Response.ok(author).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        boolean removed = DataStore.removeAuthor(id);
        if (!removed) {
            throw new AuthorNotFoundException("Author not found with ID: " + id);
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}/books")
    public Response getBooksByAuthor(@PathParam("id") int id) {
        Author author = DataStore.getAuthor(id);
        if (author == null) {
            throw new AuthorNotFoundException("Author not found with ID: " + id);
        }

        List<Book> books = DataStore.getBooksByAuthor(id);
        return Response.ok(books).build();
    }
}
