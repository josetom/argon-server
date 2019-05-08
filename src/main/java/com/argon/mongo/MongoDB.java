package com.argon.mongo;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.reactivex.impl.AsyncResultSingle;

public class MongoDB {

    private static MongoDB instance;
    private static final String DEFAULT_POOL_NAME = "ArgonMongoPool";

    private MongoClient client;

    private MongoDB() {

    }

    public static MongoDB initialize(Vertx vertx, JsonObject config, String poolName) {
        if(instance == null) {
            instance = new MongoDB();
        }
        instance.client = MongoClient.createShared(vertx, config, poolName);
        return instance;
    }

    public static MongoDB initialize(Vertx vertx, JsonObject config) {
        return initialize(vertx, config, DEFAULT_POOL_NAME);
    }

    public static Single<JsonObject> findOne(String collection, JsonObject query) {
        return findOne(collection, query, new JsonObject());
    }

    public static Single<JsonObject> findOne(String collection, JsonObject query, JsonObject fields) {
        return AsyncResultSingle.toSingle(asyncResultHandler -> {
           instance.client.findOne(collection, query, fields, asyncResultHandler);
        });
    }

    public static Single<JsonObject> insert(String collection, JsonObject document) {
        return AsyncResultSingle.<String>toSingle(asyncResultHandler -> {
            instance.client.insert(collection, document, asyncResultHandler);
        }).map(id -> {
            return document;
        });
    }

}
