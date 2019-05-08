package com.argon.annotations;

import io.vertx.core.http.HttpMethod;

public @interface RouteMapping {

    String path();

    HttpMethod[] httpMethods();

}
