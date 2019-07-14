package com.argon.actions;

import com.argon.annotations.RouteMapping;
import com.argon.db.Database;
import com.argon.model.Tables;
import io.reactivex.Single;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

@RouteMapping(path = "/login", httpMethods = {HttpMethod.POST})
public class LoginAction extends AbstractAction {

    @Override
    public Single<JsonObject> doPost(JsonObject params) {

        return Database.rxQuery(Database.sql().select()
                .from(Tables.USERS)
                .where(Tables.USERS.EMAIL.equal(params.getString("email")))
        ).map(resultSet -> {
            return resultSet.toJson();
        });

    }

}
