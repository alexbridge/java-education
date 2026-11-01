package util.concurrent;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class ThreadExecutors {

    public static void main(String[] args) {
        ExecutorService service = null;
        Runnable task1 = () -> System.out.println("Runnable task 1");
        Runnable task2 = () -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("Runnable task 2, record: " + i);
            }
        };
        try {
            service = Executors.newSingleThreadExecutor();

            System.out.println("begin");

            service.execute(task1);
            service.execute(task2);
            service.execute(task1);

            Future<Optional<String>> future = service.submit(() -> {
                System.out.println("Hello");
                return Optional.of("Result of a future");
            });

            System.out.printf("Future cancelled: %s%n", future.isCancelled());
            System.out.printf("Future done: %s%n", future.isDone());

            try {
                Optional<String> res = future.get(10, TimeUnit.SECONDS);
                res.ifPresent(System.out::println);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
            System.out.printf("Future cancelled: %s%n", future.isCancelled());
            System.out.printf("Future done: %s%n", future.isDone());

            System.out.println("end");
        } finally {
            if (service != null) service.shutdown();
        }

        try {
            service.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            invokeAll();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        try {
            scheduled();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void invokeAll() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        System.out.println("InvokeAll begin");

        Callable<String> task = () -> "result";
        List<Future<String>> list = null;

        list = service.invokeAll(List.of(task, task, task));
        for (Future<String> future : list) {
            System.out.println(future.get());
        }
        System.out.println("InvokeAll end");

        String data = service.invokeAny(List.of(task, task, task));
        System.out.println(data);

        service.shutdown();
    }

    private static void scheduled() throws ExecutionException, InterruptedException {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        System.out.println("scheduled begin");

        Callable<String> task = () -> "scheduled result";
        List<Future<String>> list = null;

        ScheduledFuture<String> data = service.schedule(task, 3, TimeUnit.SECONDS);
        System.out.println(data.get());

        System.out.println("scheduled end");

        service.shutdown();
    }
}
