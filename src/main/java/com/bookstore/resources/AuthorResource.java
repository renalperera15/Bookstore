package com.bookstore.resources;

import java.util.List;

import com.bookstore.models.Author;
import com.bookstore.models.Book;
import com.bookstore.storage.DataStore;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    @POST
    public Response addAuthor(Author author) {
        if (author == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Author data is missing.").build();
        }
        DataStore.addAuthor(author);
        return Response.status(Response.Status.CREATED).entity(author).build();
    }

    @GET
    public Response getAllAuthors() {
        List<Author> authors = DataStore.getAllAuthors();
        if (authors == null || authors.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No authors found.").build();
        }
        return Response.ok(authors).build();
    }

    @GET
    @Path("/{id}")
    public Response getAuthor(@PathParam("id") int id) {
        Author author = DataStore.getAuthor(id);
        if (author == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Author not found with ID: " + id).build();
        }
        return Response.ok(author).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAuthor(@PathParam("id") int id, Author updatedAuthor) {
        System.out.println("Updating author with ID: " + id);
        System.out.println("Received author data: " + updatedAuthor);

        if (updatedAuthor == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Updated author data is missing.").build();
        }

        Author author = DataStore.getAuthor(id);
        if (author == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Author not found with ID: " + id).build();
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
            return Response.status(Response.Status.NOT_FOUND).entity("Author not found with ID: " + id).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}/books")
    public Response getBooksByAuthor(@PathParam("id") int id) {
        Author author = DataStore.getAuthor(id);
        if (author == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Author not found with ID: " + id).build();
        }

        List<Book> books = DataStore.getBooksByAuthor(id);
        if (books == null || books.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No books found for author with ID: " + id).build();
        }
        return Response.ok(books).build();
    }
}
