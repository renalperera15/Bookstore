package com.bookstore;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Main entry point for the Bookstore API.
 * This class registers all RESTful resources under the "/api" path.
 */
@ApplicationPath("/api") // Base URL for API endpoints
public class BookstoreApplication extends Application {
    // No need to implement anything here, JAX-RS will auto-discover resources
}
