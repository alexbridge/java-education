package edu.java28;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Module Import Declarations - [final].
 * <p>
 * Feature focus:
 * - Simplifies source files with whole-module imports: {@code import module java.base;}.
 * - Makes all public types across exported packages of a module available with a single declaration.
 */
public class ModuleImportDeclarations {

    public static void main(String[] args) {
        System.out.println("=== Module Import Declarations Demo ===");
        System.out.println("With 'import module java.base;', all exported classes in java.base become available.");

        var items = List.of("alpha", "beta", "gamma", "delta");
        var groupedByLength = items.stream()
                .collect(Collectors.groupingBy(String::length));

        var path = Path.of("sample.txt");

        System.out.println("Grouped: " + groupedByLength);
        System.out.println("Path: " + path.toAbsolutePath());
    }
}
