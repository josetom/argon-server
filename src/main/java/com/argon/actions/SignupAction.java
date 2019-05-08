package com.argon.actions;

import com.argon.annotations.RouteMapping;
import com.argon.helpers.Constants;
import com.argon.helpers.ResponseText;
import com.argon.mongo.MongoDB;
import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import io.reactivex.Single;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.handler.impl.HttpStatusException;
import org.apache.http.HttpStatus;

@RouteMapping(path = "/signup", httpMethods = {HttpMethod.POST})
public class SignupAction extends AbstractAction {

    public Single<JsonObject> doPost(JsonObject params) {

        return MongoDB.insert(Constants.MONGO.COLLECTION_USERS, params)
                .map(result-> {
                    JsonObject response = new JsonObject();
                    response.put("user", result);
                    return response;
                })
                .doOnError(throwable -> {
                    if(throwable instanceof MongoWriteException) {
                        if(ErrorCategory.DUPLICATE_KEY.equals(ErrorCategory.fromErrorCode(((MongoWriteException) throwable).getCode()))) {
                            throw new HttpStatusException(HttpStatus.SC_CONFLICT, ResponseText.SIGNUP.EMAIL_EXISTS);
//                            throwable.initCause(new HttpStatusException(HttpStatus.SC_CONFLICT, ResponseText.SIGNUP.EMAIL_EXISTS));
//                            throwable = new HttpStatusException(HttpStatus.SC_CONFLICT, ResponseText.SIGNUP.EMAIL_EXISTS).initCause(throwable);
                        }
                    }
                });
    }

}
