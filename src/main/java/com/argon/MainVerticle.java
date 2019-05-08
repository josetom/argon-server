package com.argon;

import com.argon.config.ConfigLoader;
import com.argon.helpers.Constants;
import com.argon.mongo.MongoDB;
import com.argon.router.RouteHandler;
import io.reactivex.Completable;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.AbstractVerticle;

public class MainVerticle extends AbstractVerticle {

    Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class);

    @Override
    public Completable rxStart() {
        super.rxStart();

        return ConfigLoader.loadConfig(vertx)
                .rxGetConfig()
                .map(config -> {
                    MongoDB.initialize(vertx.getDelegate(), config.getJsonObject(Constants.MONGO._CONFIG));
                    return config;
                })
                .flatMapCompletable(config -> {
                    Completable completable = createServer();
                    LOGGER.info("Started server");
                    return completable;
                });

    }

    private Completable createServer() {
        return vertx.createHttpServer()
                .requestHandler(RouteHandler.createRouter(vertx))
                .rxListen(Vertx.currentContext().config().getInteger(Constants.ARGON.PORT, 9000))
                .ignoreElement();
    }


}
