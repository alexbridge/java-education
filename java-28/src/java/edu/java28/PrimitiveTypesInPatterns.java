package edu.java28;

/**
 * Primitive Types in Patterns, instanceof, and switch - [preview].
 * <p>
 * Feature focus:
 * - Extends pattern matching to primitive types in {@code switch} and {@code instanceof}.
 * - Removes the restriction that patterns must match reference types only.
 * - Allows exact type tests, value checks, and guarded patterns for primitive types.
 */
public class PrimitiveTypesInPatterns {

    public static void main(String[] args) {
        System.out.println("=== Primitive Types in Patterns Demo ===");

        classifyNumber(42);
        classifyNumber(1000);
        classifyNumber((byte) 7);
        classifyNumber(3.14159);
        classifyNumber(250);
    }

    private static void classifyNumber(Object obj) {
        // Primitive pattern matching supports matching primitives directly
        var description = switch (obj) {
            case Byte b -> "Byte value: " + b;
            case Integer i when i < 100 -> "Small integer: " + i;
            case Integer i -> "Large integer: " + i;
            case Double d -> "Floating point double: " + d;
            case null -> "Null value";
            default -> "Other type: " + obj;
        };

        System.out.println(obj + " => " + description);
    }
}
