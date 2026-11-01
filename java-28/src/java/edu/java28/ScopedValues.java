package edu.java28;

/**
 * Scoped Values - [final].
 * <p>
 * Feature focus:
 * - Immutable, bounded-lifetime context sharing across threads via {@link ScopedValue}.
 * - Modern, lightweight replacement for {@link ThreadLocal}.
 * - Inherited efficiently by child virtual threads with minimal memory overhead.
 */
public class ScopedValues {

    private static final ScopedValue<String> CURRENT_USER = ScopedValue.newInstance();
    private static final ScopedValue<String> REQUEST_ID = ScopedValue.newInstance();

    public static void main(String[] args) {
        System.out.println("=== Scoped Values Demo ===");

        // Bound strictly to the execution of the runnable
        ScopedValue.where(CURRENT_USER, "admin_user")
                   .where(REQUEST_ID, "req-98765")
                   .run(() -> {
                       logAction("Entering secured operation");
                       processRequest();
                   });

        System.out.println("Outside scope, CURRENT_USER is bound: " + CURRENT_USER.isBound());
    }

    private static void processRequest() {
        logAction("Processing request payload");
    }

    private static void logAction(String message) {
        var user = CURRENT_USER.orElse("anonymous");
        var reqId = REQUEST_ID.orElse("none");
        System.out.printf("[%s | user=%s] %s%n", reqId, user, message);
    }
}
