package com.argon.router;

import com.argon.actions.AbstractAction;
import com.argon.actions.LoginAction;
import com.argon.actions.SignupAction;
import io.vertx.core.http.HttpMethod;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import io.vertx.reactivex.ext.web.handler.CorsHandler;

public class RouteHandler {

    public static Router createRouter(Vertx vertx) {
        Router router = Router.router(vertx);

        router.route().handler(corsHandler());
        router.route().handler(BodyHandler.create());

        router.options().handler(routingContext -> {
            routingContext.response().end();
        });

        router.post("/login").handler(routingContext -> {
            AbstractAction action = new LoginAction();
            action.doAction(routingContext);
        });

        router.post("/signup").handler(routingContext -> {
            AbstractAction action = new SignupAction();
            action.doAction(routingContext);
        });

        return router;
    }

    private static CorsHandler corsHandler() {

        return CorsHandler.create("*")
                // Allowed Headers
                .allowedHeader("Access-Control-Allow-Origin")
                .allowedHeader("origin")
                .allowedHeader("Content-Type")
                .allowedHeader("accept")
                // Allowed methods
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.OPTIONS)
                .allowedMethod(HttpMethod.DELETE)
                .allowedMethod(HttpMethod.PATCH)
                .allowedMethod(HttpMethod.PUT);
    }

}
