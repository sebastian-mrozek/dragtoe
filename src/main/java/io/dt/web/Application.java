package io.dt.web;

import io.avaje.http.api.WebRoutes;
import io.avaje.inject.SystemContext;
import io.javalin.Javalin;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import java.util.List;

public class Application {
    private final Javalin server;

    public static void main(String[] args) {
        var app = new Application();
        app.start();
    }

    public Application() {
        server = Javalin.create();

        List<WebRoutes> webRoutes = SystemContext.context().getBeans(WebRoutes.class);
        server.routes(() -> webRoutes.forEach(WebRoutes::registerRoutes));

        registerExceptionMapper(OptimisticLockException.class, 400, "Update out of date");
        registerExceptionMapper(PersistenceException.class, 500, "Database error");
        registerExceptionMapper(Exception.class, 500, "Application error");
    }

    public void start() {
        server.start();
    }

    public void start(int port) {
        server.start(port);
    }

    public void stop() {
        server.stop();
    }

    private <T extends Exception> void registerExceptionMapper(Class<T> exceptionClass, int statusCode, String message) {
        server.exception(exceptionClass, ExceptionHandlerFactory.createHandler(exceptionClass, statusCode, message));
    }
}
