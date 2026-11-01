package util.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomics {
    static int nonAtomic = 0;
    static AtomicInteger atomic = new AtomicInteger(10);

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                nonAtomic++;
                int got = atomic.addAndGet(1);
                System.out.println("non atomic: " + nonAtomic);
                System.out.println("atomic: " + got);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                nonAtomic++;
                int got = atomic.addAndGet(1);
                System.out.println("non atomic: " + nonAtomic);
                System.out.println("atomic: " + got);
            }
        }).start();
    }
}
