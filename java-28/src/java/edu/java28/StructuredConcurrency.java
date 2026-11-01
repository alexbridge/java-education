package edu.java28;

import java.util.concurrent.StructuredTaskScope;

/**
 * Structured Concurrency - [preview].
 * <p>
 * Feature focus:
 * - Coordinates concurrent subtasks as a single atomic unit of work via {@link StructuredTaskScope}.
 * - In Java 25+ (JEP 505), StructuredTaskScope is a sealed interface opened via {@code StructuredTaskScope.open()}.
 * - Guarantees error propagation, automatic subtask cancellation, and clean lifetimes.
 * - Prevents thread leaks and orphan threads when tasks fail or cancel.
 */
public class StructuredConcurrency {

    record UserProfile(String id, String name) {}
    record UserOrders(String id, int count) {}
    record UserDashboard(UserProfile profile, UserOrders orders) {}

    public static void main(String[] args) {
        System.out.println("=== Structured Concurrency Demo ===");

        try {
            var dashboard = fetchUserDashboard("user-42");
            System.out.println("Fetched dashboard: " + dashboard);
        } catch (Exception e) {
            System.err.println("Failed to fetch dashboard: " + e.getMessage());
        }
    }

    private static UserDashboard fetchUserDashboard(String userId) throws Exception {
        // In Java 25+ (JEP 505): StructuredTaskScope is an interface opened with StructuredTaskScope.open()
        try (var scope = StructuredTaskScope.open()) {
            var profileTask = scope.fork(() -> fetchProfile(userId));
            var ordersTask = scope.fork(() -> fetchOrders(userId));

            scope.join(); // Wait for all subtasks to complete

            return new UserDashboard(profileTask.get(), ordersTask.get());
        }
    }

    private static UserProfile fetchProfile(String userId) throws InterruptedException {
        Thread.sleep(50);
        return new UserProfile(userId, "Alice");
    }

    private static UserOrders fetchOrders(String userId) throws InterruptedException {
        Thread.sleep(50);
        return new UserOrders(userId, 5);
    }
}
