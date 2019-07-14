package com.argon.helpers;

public interface Constants {

    static String $(String key) {
        return _PREFIX + key;
    }

    String _PREFIX = "argon.vertx.";

    String PORT =  $("port");

}
