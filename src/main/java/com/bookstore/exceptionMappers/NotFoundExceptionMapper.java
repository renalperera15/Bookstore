package com.bookstore.exceptionMappers;

import java.util.Collections;

import com.bookstore.exceptions.BookNotFoundException;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

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
