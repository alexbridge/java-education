package util.concurrent;

import java.time.Instant;
import java.util.concurrent.*;

public class Callables {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);

        Future<String> result = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                return Instant.now().toString();
            }
        });

        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        service.shutdown();
    }
}
