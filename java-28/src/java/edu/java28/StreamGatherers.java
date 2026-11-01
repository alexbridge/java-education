package edu.java28;

import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

/**
 * Stream Gatherers - [final].
 * <p>
 * Feature focus:
 * - Enhances the Stream API with custom, user-defined intermediate operations via {@code Stream.gather(...)}.
 * - Supports windowing, folding, scanning, and stateful/concurrent stream transformations.
 */
public class StreamGatherers {

    public static void main(String[] args) {
        System.out.println("=== Stream Gatherers Demo ===");

        // Windowing stream elements into fixed-size lists
        var windows = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .gather(Gatherers.windowFixed(3))
                .toList();

        System.out.println("Fixed windows of 3: " + windows);

        // Running fold/scan on elements
        var scanned = Stream.of("a", "b", "c", "d")
                .gather(Gatherers.scan(() -> "", (acc, elem) -> acc + elem))
                .toList();

        System.out.println("Cumulative scan: " + scanned);
    }
}
