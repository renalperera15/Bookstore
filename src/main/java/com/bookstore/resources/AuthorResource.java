package com.bookstore.resources;

import java.util.List;

import com.bookstore.models.Author;
import com.bookstore.models.Book;
import com.bookstore.storage.DataStore;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    @POST
    public Response addAuthor(Author author) {
        if (author == null || author.getName() == null || author.getName().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Invalid author data. Name is required.")
                           .build();
        }

        DataStore.addAuthor(author);
        return Response.status(Response.Status.CREATED)
                       .entity(author)
                       .build();
    }

    @GET
    public Response getAllAuthors() {
        List<Author> authors = DataStore.getAllAuthors();

        return Response.ok(authors).build(); // Return empty array if no authors found
    }

    @GET
    @Path("/{id}")
    public Response getAuthor(@PathParam("id") int id) {
        Author author = DataStore.getAuthor(id);

        if (author == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Author not found with ID: " + id)
                           .build();
        }

        return Response.ok(author).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAuthor(@PathParam("id") int id, Author updatedAuthor) {
        if (updatedAuthor == null || updatedAuthor.getName() == null || updatedAuthor.getName().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Invalid update data. Author name is required.")
                           .build();
        }

        Author existingAuthor = DataStore.getAuthor(id);
        if (existingAuthor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Author not found with ID: " + id)
                           .build();
        }

        existingAuthor.setName(updatedAuthor.getName());
        existingAuthor.setBiography(updatedAuthor.getBiography());

        return Response.ok(existingAuthor).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        boolean removed = DataStore.removeAuthor(id);

        if (!removed) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Author not found with ID: " + id)
                           .build();
        }

        return Response.noContent().build(); // 204 No Content on successful deletion
    }

    @GET
    @Path("/{id}/books")
    public Response getBooksByAuthor(@PathParam("id") int id) {
        Author author = DataStore.getAuthor(id);
        if (author == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Author not found with ID: " + id)
                           .build();
        }

        List<Book> books = DataStore.getBooksByAuthor(id);

        return Response.ok(books).build(); // Return empty array if no books
    }
}
