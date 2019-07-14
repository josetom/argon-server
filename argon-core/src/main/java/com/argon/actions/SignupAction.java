package com.argon.actions;

import com.argon.annotations.RouteMapping;
import com.argon.db.Database;
import com.argon.model.Tables;
import com.argon.model.tables.records.UsersRecord;
import io.reactivex.Single;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

@RouteMapping(path = "/signup", httpMethods = {HttpMethod.POST})
public class SignupAction extends AbstractAction {

    public Single<JsonObject> doPost(JsonObject params) {

        UsersRecord usersRecord = new UsersRecord();
        usersRecord.setEmail(params.getString("email"));

        return Database.rxUpdate(Database.sql().insertInto(Tables.USERS)
                .set(usersRecord)
        ).map(updateResult -> {
            return updateResult.toJson();
        });

    }

}
