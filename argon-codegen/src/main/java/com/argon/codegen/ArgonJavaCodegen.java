package com.argon.codegen;

import org.openapitools.codegen.CodegenType;
import org.openapitools.codegen.languages.AbstractJavaCodegen;

public class ArgonJavaCodegen extends AbstractJavaCodegen {

    public static String NAME = "argon-java";

    /**
     * Configures the type of generator.
     *
     * @return the CodegenType for this generator
     * @see org.openapitools.codegen.CodegenType
     */
    public CodegenType getTag() {
        return CodegenType.SERVER;
    }

    /**
     * Configures a friendly name for the generator.  This will be used by the generator
     * to select the library with the -g flag.
     *
     * @return the friendly name for the generator
     */
    public String getName() {
        return NAME;
    }

    /**
     * Returns human-friendly help for the generator. Provide the consumer with help tips,
     * parameters here
     *
     * @return A string value for the help message
     */
    public String getHelp() {
        return "Generates a argon-java Server library.";
    }

}
