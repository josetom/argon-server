package com.argon.actions;

import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.handler.impl.HttpStatusException;
import org.apache.http.HttpStatus;

public interface Action {

    default Single<JsonObject> noActionMapped(JsonObject params) {
        throw new HttpStatusException(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Action is not implemented");
    }

    default Single<JsonObject> doPost(JsonObject params) {
        return noActionMapped(params);
    }

}
