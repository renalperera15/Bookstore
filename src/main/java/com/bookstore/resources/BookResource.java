package com.bookstore.resources;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.bookstore.models.Book;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        System.out.println("Books size: " + books.size());
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
