package edu.java28;

/**
 * Value Classes & Objects (Project Valhalla) - [preview].
 * <p>
 * Feature focus:
 * - {@code value class} declarations without object identity.
 * - Equality determined by field state rather than memory reference.
 * - Memory layout flattening in heaps and arrays without pointer indirection.
 */
public class ValueClasses {

    // Value objects (Valhalla): identityless, immutable, flat in memory
    // Conceptually: value class Point(int x, int y) { ... }
    public record Point(int x, int y) {
        public Point translate(int dx, int dy) {
            return new Point(x + dx, y + dy);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Valhalla Value Classes Demo ===");

        var p1 = new Point(10, 20);
        var p2 = new Point(10, 20);
        var p3 = p1.translate(5, 5);

        // Value classes check equality by state, not object identity
        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);
        System.out.println("p1.equals(p2) => " + p1.equals(p2));
        System.out.println("p3 (translated) => " + p3);

        // In Valhalla, value objects have no lock monitor and no identity hashcode
        System.out.println("Value objects guarantee immutability and zero-pointer array flattening.");
    }
}
