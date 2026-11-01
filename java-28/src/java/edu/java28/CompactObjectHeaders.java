package edu.java28;

/**
 * Compact Object Headers (Project Lilliput) - [preview].
 * <p>
 * Feature focus:
 * - Reduces 64-bit JVM object headers from 128/96 bits down to 64 bits.
 * - Drastically decreases object memory overhead, improves CPU cache utilization, and shrinks heap footprint.
 * - Enabled via JVM flag: {@code -XX:+UnlockExperimentalVMOptions -XX:+UseCompactObjectHeaders}.
 */
public class CompactObjectHeaders {

    public static void main(String[] args) {
        System.out.println("=== Compact Object Headers (Project Lilliput) Demo ===");

        // Traditional 64-bit JVM header: 128 bits (96 bits with compressed class pointers)
        // Lilliput compact headers: 64 bits total per object
        var objectCount = 10_000_000;
        var bytesSavedPerObject = 8L; // 64-bit header vs 128-bit header
        var totalSavingsMb = (objectCount * bytesSavedPerObject) / (1024 * 1024);

        System.out.printf("Allocating %,d objects saves ~%d MB of heap overhead with compact headers.%n",
                objectCount, totalSavingsMb);
        System.out.println("JVM Flag to enable in JDK 24+: -XX:+UnlockExperimentalVMOptions -XX:+UseCompactObjectHeaders");
    }
}
