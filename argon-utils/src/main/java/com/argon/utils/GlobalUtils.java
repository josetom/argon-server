package com.argon.utils;

import com.argon.exceptions.ArgonAssertException;

public class GlobalUtils {

    public static void assert_(boolean expr) {
        assert_(expr, "");
    }

    public static void assert_(boolean expr, String message) {
        if (!expr) {
            throw new ArgonAssertException(message);
        }
    }

    public static <TYPE> TYPE get(TYPE value, TYPE defaultValue) {
        return (value != null) ? value : defaultValue;
    }

}
