package com.argon.jooq.generator;

import com.argon.utils.DatabaseConstants;
import com.argon.utils.DatabaseHelper;
import com.argon.utils.Env;
import com.argon.utils.GlobalUtils;
import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ModelGenerator extends GenerationTool {

    public static void main(String[] args) throws Exception {
        Env.init();
        GenerationTool.generate(config());
    }

    public static Configuration config() {
        Configuration configuration = new Configuration();
        configuration
                .withJdbc(new Jdbc()
                        .withDriver(Env.getString(DatabaseConstants.DRIVER))
                        .withUrl(DatabaseHelper.getUrl())
                        .withUser(Env.getString(DatabaseConstants.USERNAME))
                        .withPassword(Env.getString(DatabaseConstants.PASSWORD))
                );

        ArrayList<LinkedHashMap<String, String>> schemas = (ArrayList<LinkedHashMap<String, String>>) Env.get(DatabaseConstants.SCHEMA_GENERATOR);
        if(schemas != null) {
            schemas.forEach(schemaItem -> {
                configuration
                        .withGenerator(new Generator()
                                .withStrategy(new Strategy())
                                .withDatabase(new Database()
                                        .withInputSchema(schemaItem.get(DatabaseConstants.SCHEMA_GENERATOR_SCHEMA))
                                )
                                .withGenerate(new Generate()
//                                        .withPojos(true)
//                                        .withValidationAnnotations(true)
                                        .withRecordsImplementingRecordN(false)
                                )
                                .withTarget(new Target()
                                        .withDirectory(GlobalUtils.get(schemaItem.get(DatabaseConstants.SCHEMA_GENERATOR_OUTPUT_PATH), DatabaseConstants.DEFAULT_SCHEMA_GENERATOR_OUTPUT_PATH))
                                        .withPackageName(GlobalUtils.get(schemaItem.get(DatabaseConstants.SCHEMA_GENERATOR_PACKAGE_NAME), DatabaseConstants.DEFAULT_SCHEMA_GENERATOR_PACKAGE_NAME))
                                )
                        );
            });
        }

        return configuration;
    }

}
