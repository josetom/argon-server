package com.argon;

import com.argon.config.ConfigLoader;
import com.argon.db.Database;
import com.argon.helpers.Constants;
import com.argon.router.RouteHandler;
import com.argon.utils.Env;
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
                    Env.init();
                    Database.init(vertx);
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
                .rxListen(Vertx.currentContext().config().getInteger(Constants.PORT, 9000))
                .ignoreElement();
    }


}
