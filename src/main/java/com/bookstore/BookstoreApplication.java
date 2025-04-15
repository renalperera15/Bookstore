package com.bookstore;

import java.io.IOException;
import java.net.URI;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.sun.net.httpserver.HttpServer;

public class BookstoreApplication {

    public static final String BASE_URI = "http://localhost:8080/api/";

    public static org.glassfish.grizzly.http.server.HttpServer startServer() {
        final ResourceConfig config = new ResourceConfig()
                .packages("com.bookstore.resources", "com.bookstore.exceptionMappers");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
    }

    public static void main(String[] args) throws IOException {
        final org.glassfish.grizzly.http.server.HttpServer server = startServer();
        System.out.println("Bookstore API started at " + BASE_URI);
        System.out.println("Hit enter to stop it...");
        System.in.read();
        ((org.glassfish.grizzly.http.server.HttpServer) server).shutdownNow();
    }
}
