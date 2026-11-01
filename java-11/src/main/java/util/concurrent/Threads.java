package util.concurrent;

public class Threads {

    public static void main(String[] args) throws InterruptedException {
        Runnable task1 = () -> System.out.println("Runnable task 1");
        Runnable task2 = () -> System.out.println("Runnable task 2");

        Thread th1 = new Thread(task1);
        Thread th2 = new Thread(task2);

        th1.start();
        th1.join();

        th2.start();
        th2.join();

        interrupting();
    }

    private static void interrupting() {
        Runnable task1 = () -> {
            System.out.println("Interrupting task");
            Thread th = Thread.currentThread();
            while (!th.isInterrupted()) {
                System.out.println("Interrupting task try sleep");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupting sleep interrupted");
                    break;
                }
            }
            System.out.println("Interrupting task interrupted");
        };

        Thread th = new Thread(task1);
        th.start();
        try {
            Thread.sleep(200);
            th.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
