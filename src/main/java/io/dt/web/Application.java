package io.dt.web;

import io.avaje.http.api.WebRoutes;
import io.avaje.inject.SystemContext;
import io.javalin.Javalin;

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
}
