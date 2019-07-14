package com.argon.utils;

public class DatabaseHelper {

    public static String getUrl() {
        return new StringBuilder()
                .append(Env.getString(DatabaseConstants.PROTOCOL))
                .append("://")
                .append(Env.getString(DatabaseConstants.HOST))
                .append(":")
                .append(Env.get(DatabaseConstants.PORT))
                .append("/")
                .append(Env.getString(DatabaseConstants.DATABASE))
                .toString();
    }

}
