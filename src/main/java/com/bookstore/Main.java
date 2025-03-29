package com.bookstore;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.*;

// Data Models
class Book {
    public int id;
    public String title;
    public String author;
    public String isbn;
    public int publicationYear;
    public double price;
    public int stock;

    public Book(int id, String title, String author, String isbn, int publicationYear, double price, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.price = price;
        this.stock = stock;
    }
}

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    private static final Map<Integer, Book> books = new HashMap<>();
    private static int bookIdCounter = 1;

    @POST
    public Response createBook(Book book) {
        book.id = bookIdCounter++;
        books.put(book.id, book);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @GET
    public Collection<Book> getAllBooks() {
        return books.values();
    }

    @GET
    @Path("{id}")
    public Response getBook(@PathParam("id") int id) {
        Book book = books.get(id);
        if (book == null) {
            throw new WebApplicationException("Book not found", Response.Status.NOT_FOUND);
        }
        return Response.ok(book).build();
    }

    @PUT
    @Path("{id}")
    public Response updateBook(@PathParam("id") int id, Book updatedBook) {
        if (!books.containsKey(id)) {
            throw new WebApplicationException("Book not found", Response.Status.NOT_FOUND);
        }
        updatedBook.id = id;
        books.put(id, updatedBook);
        return Response.ok(updatedBook).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteBook(@PathParam("id") int id) {
        if (!books.containsKey(id)) {
            throw new WebApplicationException("Book not found", Response.Status.NOT_FOUND);
        }
        books.remove(id);
        return Response.noContent().build();
    }
}

// Exception Handling
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException exception) {
        return Response.status(exception.getResponse().getStatus())
                .entity(Collections.singletonMap("error", exception.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
