package com.bookstore.resources;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.bookstore.models.Book;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    private static final Map<Integer, Book> books = new HashMap<>();
    private static int bookIdCounter = 1;

    @POST
    public Response createBook(Book book) {
        book = new Book(bookIdCounter++, book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPublicationYear(), book.getPrice(), book.getStock());
        books.put(book.getId(), book);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @GET
    public Collection<Book> getAllBooks() {
        return books.values();
    }

    @GET
    @Path("/{id}")
    public Response getBook(@PathParam("id") int id) {
        Book book = books.get(id);
        if (book == null) {
            throw new WebApplicationException("Book not found", Response.Status.NOT_FOUND);
        }
        return Response.ok(book).build();
    }
}
