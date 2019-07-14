package com.argon.utils;

import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;

public class Env {

    private static HashMap<String, Object> config = new HashMap<>();

    public static Object get(String key) {
        assertIfInit();
        return config.get(key);
    }

    public static String getString(String key) {
        return (String) get(key);
    }

    public static <TYPE> TYPE get(String key, TYPE defaultVal) {
        Object val = get(key);
        return (val != null) ? (TYPE) val : defaultVal;
    }

    public static void init() {
        loadDBConfig();
    }

    private static void loadDBConfig(){
        Yaml yaml = new Yaml();
        config.putAll(yaml.load(Env.class.getResourceAsStream("/db_config.yaml")));
    }

    private static void assertIfInit() {
        GlobalUtils.assert_(config != null, "Env not initialized");
    }

}
