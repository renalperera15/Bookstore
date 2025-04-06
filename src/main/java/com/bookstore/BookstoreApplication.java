package com.bookstore;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.core.Application;

public class BookstoreApplication extends Application {

    public static final String BASE_URI = "http://localhost:8080/api/";

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("com.bookstore.resources");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println("Bookstore API started at " + BASE_URI);
        System.out.println("Press Enter to stop...");
        System.in.read();
        server.shutdownNow();
    }
}
