package com.bookstore.exceptionMappers;

import com.bookstore.exceptions.BookNotFoundException;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<BookNotFoundException> {
    @Override
    public Response toResponse(BookNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(Collections.singletonMap("error", e.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
