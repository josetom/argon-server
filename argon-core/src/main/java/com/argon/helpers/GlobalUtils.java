package com.argon.helpers;

public class GlobalUtils {

    public static void assertExpression(boolean expr, String message) {
        if(!expr) {
            throw  new RuntimeException(message);
        }
    }

}
