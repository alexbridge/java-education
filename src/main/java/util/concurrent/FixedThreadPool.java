package util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            final int o = i;
            service.submit(() -> {
                System.out.format("Runnable task %d\n", o);
            });
        }

        service.shutdown();
    }
}
