package io.dt.web;

import io.javalin.http.Context;
import io.javalin.http.ExceptionHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionHandlerFactory {

    public static final Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);

    public static <T extends Exception> ExceptionHandler<T> createHandler(Class<T> clazz, int code, String message) {

        return (@NotNull T exception, @NotNull Context ctx) -> {

            LOG.warn(exception.getMessage(), exception);

            ctx.status(code);
            ctx.result(message);
        };
    }
}
