package lang.exception;

public class SneakyThrow {
    public static void main(String[] args) {
        Thread th = new Thread(() -> {
            throw new IllegalStateException("error");
        });
        try {
          th.start();
        } catch (IllegalStateException e) {
            System.out.printf("Not catched %s%n", e.getMessage());
        }

        Runnable action = () -> {
            throw new IllegalStateException("error");
        };
        try {
            action.run();
        } catch (IllegalStateException e) {
            System.out.printf("Catched %s%n", e.getMessage());
        }
    }
}
