package com.argon.actions;

import com.argon.annotations.RouteMapping;
import com.argon.helpers.Constants;
import com.argon.mongo.MongoDB;
import io.reactivex.Single;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

@RouteMapping(path = "/login", httpMethods = {HttpMethod.POST})
public class LoginAction extends AbstractAction {

    @Override
    public Single<JsonObject> doPost(JsonObject params) {
        JsonObject query = new JsonObject()
                .put("email", params.getString("email"));

        return MongoDB.findOne(Constants.MONGO.COLLECTION_USERS, query)
                .map(result-> {
                    JsonObject response = new JsonObject();
                    response.put("user", result);
                    return response;
                });
    }

}
