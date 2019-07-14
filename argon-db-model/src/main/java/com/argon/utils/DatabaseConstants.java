package com.argon.utils;

public interface DatabaseConstants {

    String DRIVER = "argon.db.driver";
    String PROTOCOL = "argon.db.protocol";
    String HOST = "argon.db.host";
    String PORT = "argon.db.port";
    String DATABASE = "argon.db.database";
    String USERNAME = "argon.db.username";
    String PASSWORD = "argon.db.password";

    String SCHEMA_GENERATOR = "argon.db.schema_generator";
    String SCHEMA_GENERATOR_SCHEMA = "schema";
    String SCHEMA_GENERATOR_OUTPUT_PATH = "output_path";
    String SCHEMA_GENERATOR_PACKAGE_NAME = "package_name";

    String DEFAULT_SCHEMA_GENERATOR_OUTPUT_PATH = "src/main/generated";
    String DEFAULT_SCHEMA_GENERATOR_PACKAGE_NAME = "com.argon.model";

}
