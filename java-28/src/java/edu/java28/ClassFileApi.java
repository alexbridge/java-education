package edu.java28;

import java.io.InputStream;
import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassModel;
import java.lang.classfile.MethodModel;

/**
 * Class-File API ({@code java.lang.classfile}) - [final].
 * <p>
 * Feature focus:
 * - Standard JDK API for parsing, generating, and transforming Java class files.
 * - Replaces external third-party bytecode manipulation libraries like ASM.
 */
public class ClassFileApi {

    public static void main(String[] args) throws Exception {
        System.out.println("=== Class-File API Demo ===");

        // Load bytecode of this class
        var classResource = ClassFileApi.class.getSimpleName() + ".class";
        try (InputStream in = ClassFileApi.class.getResourceAsStream(classResource)) {
            if (in == null) {
                System.out.println("ClassFile API inspects and generates bytecode directly without 3rd-party libs (ASM).");
                System.out.println("Usage: ClassFile.of().parse(bytes) -> inspect methods, fields, attributes.");
                return;
            }

            var bytes = in.readAllBytes();
            var model = ClassFile.of().parse(bytes);

            System.out.println("Class Name: " + model.thisClass().asInternalName());
            System.out.println("Major Version: " + model.majorVersion());
            System.out.println("Methods count: " + model.methods().size());

            for (MethodModel method : model.methods()) {
                System.out.println(" - Method: " + method.methodName().stringValue() + " | type: " + method.methodType().stringValue());
            }
        }
    }
}
