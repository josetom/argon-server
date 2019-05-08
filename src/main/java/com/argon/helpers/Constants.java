package com.argon.helpers;

public interface Constants {

    interface ARGON {
        String _PREFIX = "argon.vertx.";

        String PORT = _PREFIX + "port";
    }

    interface MONGO {
        String _CONFIG = "mongo";

        String DB_ARGON = "argon";

        String COLLECTION_USERS = "users";
    }

}
