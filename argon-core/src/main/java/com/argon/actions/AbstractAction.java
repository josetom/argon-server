package com.argon.actions;

import com.argon.helpers.ResponseText;
import io.reactivex.Single;
import io.reactivex.exceptions.CompositeException;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.handler.impl.HttpStatusException;
import io.vertx.reactivex.core.http.HttpServerResponse;
import io.vertx.reactivex.ext.web.RoutingContext;
import org.apache.http.HttpStatus;

public abstract class AbstractAction implements Action {

    Logger LOGGER = LoggerFactory.getLogger(AbstractAction.class);

    public void doAction(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();

        execute(routingContext)
                .subscribe(jsonObject -> {
                    response.end(jsonObject.toString());
                }, throwable -> {
                    LOGGER.error(throwable);
                    if (throwable instanceof HttpStatusException) {
                        HttpStatusException hse = (HttpStatusException) throwable;
                        response.setStatusCode(hse.getStatusCode());
                        response.end(hse.getPayload());
                    } else if (throwable instanceof CompositeException &&
                            ((CompositeException) throwable).getExceptions().get(((CompositeException) throwable).size() - 1) instanceof HttpStatusException) {
                        HttpStatusException hse = (HttpStatusException) ((CompositeException) throwable).getExceptions().get(((CompositeException) throwable).size() - 1);
                        response.setStatusCode(hse.getStatusCode());
                        response.end(hse.getPayload());
                    } else {
                        response.setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                        response.end(ResponseText.GENERIC.DEFAUT_ERROR_MESSAGE);
                    }
                });
    }

    private Single<JsonObject> execute(RoutingContext routingContext) {
        if (HttpMethod.POST.equals(routingContext.request().method())) {
            return doPost(routingContext.getBodyAsJson());
        }

        return noActionMapped(routingContext.getBodyAsJson());
    }

}
