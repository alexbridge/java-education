package edu.java28;

import java.lang.reflect.Method;

/**
 * Code Reflection (Project Babylon) - [preview].
 * <p>
 * Feature focus:
 * - Provides programmatic access to expression trees and high-level code models of Java code.
 * - Enables runtime inspection and translation of Java code to GPU kernels, shaders, and SQL queries.
 */
public class CodeReflection {

    // In Project Babylon, methods can be analyzed as expression trees / code models
    // allowing direct compilation to GPU kernels, shaders, or SQL queries.
    public static int calculateTax(int price, int percent) {
        return (price * percent) / 100;
    }

    public static void main(String[] args) {
        System.out.println("=== Project Babylon Code Reflection Demo ===");

        var sampleTax = calculateTax(200, 15);
        System.out.println("Execution result: " + sampleTax);

        var methods = CodeReflection.class.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println("Inspected method: " + m.getName() + " returning " + m.getReturnType().getSimpleName());
        }

        System.out.println("Babylon exposes the high-level Code Model of the method body for runtime translation.");
    }
}
