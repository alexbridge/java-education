package util.concurrent;

public class WaitNotify {

    public static void main(String[] args) {
        Object lock = new Object();
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Start waiting");
                    lock.wait();
                    System.out.println("Stop waiting");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock) {
            System.out.println("Start notify");
            lock.notify();
        }
    }
}
