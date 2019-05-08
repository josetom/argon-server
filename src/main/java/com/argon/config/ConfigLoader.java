package com.argon.config;

import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.config.ConfigRetriever;
import io.vertx.reactivex.core.Vertx;

public class ConfigLoader {

    public static ConfigRetriever loadConfig(Vertx vertx) {

        ConfigRetrieverOptions options = new ConfigRetrieverOptions();

        loadGeneralConfig(options);
        loadMongoConfig(options);

        return ConfigRetriever.create(vertx, options);
    }

    private static ConfigRetrieverOptions loadGeneralConfig(ConfigRetrieverOptions options) {
        ConfigStoreOptions store = new ConfigStoreOptions()
                .setType("file")
                .setFormat("yaml")
                .setConfig(new JsonObject()
                        .put("path", "general-config.yaml")
                );
        return options.addStore(store);
    }

    private static ConfigRetrieverOptions loadMongoConfig(ConfigRetrieverOptions options) {
        ConfigStoreOptions store = new ConfigStoreOptions()
                .setType("file")
                .setFormat("yaml")
                .setConfig(new JsonObject()
                        .put("path", "mongo-config.yaml")
                );
        return options.addStore(store);
    }

}
