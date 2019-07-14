package com.argon.codegen;

import com.google.common.base.Strings;
import org.openapitools.codegen.ClientOptInput;
import org.openapitools.codegen.DefaultGenerator;
import org.openapitools.codegen.Generator;
import org.openapitools.codegen.config.CodegenConfigurator;

import java.util.HashMap;
import java.util.Map;

public class ArgonGenerator extends DefaultGenerator implements Generator {

    static String MS_NAME = "microServiceName";


    public static void main(String[] args) {

        String generatorName = Strings.isNullOrEmpty(args[3]) ? ArgonJavaCodegen.NAME : args[3];

        Map<String, Object> additionalProps = new HashMap<>();
        additionalProps.put(MS_NAME, args[0]);

        CodegenConfigurator configurator = new CodegenConfigurator();
        configurator.setGeneratorName(generatorName);
        configurator.setInputSpec(args[1]);
        configurator.setOutputDir(args[2]);
        configurator.setAdditionalProperties(additionalProps);
        ClientOptInput clientOptInput = configurator.toClientOptInput();
        new ArgonGenerator().opts(clientOptInput).generate();
    }

}
