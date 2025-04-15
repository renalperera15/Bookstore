package com.bookstore;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

public class BookstoreApplication extends Application {

    public static final String BASE_URI = "http://localhost:8080/api/";

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig()
            .packages("com.bookstore.resources")
            .register(GenericExceptionMapper.class); // Register the exception mapper
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println("Bookstore API started at " + BASE_URI);
        System.out.println("Press Enter to stop...");
        System.in.read();
        server.shutdownNow();
    }

    @Provider
    public static class GenericExceptionMapper implements ExceptionMapper<Throwable> {
        @Override
        public Response toResponse(Throwable exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("An unexpected error occurred: " + exception.getMessage())
                           .build();
        }
    }
}
