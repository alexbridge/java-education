package util.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Locks {
    List<String> list = new ArrayList<>();
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        Locks locks = new Locks();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                locks.addLine("Line " + i);
                System.out.println("After add: " + locks.list);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                String line = locks.getLine("Line " + i);
                System.out.println("After get: " + locks.list);
            }
        }).start();

    }

    private void addLine(String str) {
        Lock writeLock = lock.writeLock();
        try {
            writeLock.lock();
            list.add(str);
        } finally {
            writeLock.unlock();
        }
    }

    private String getLine(String str) {
        Lock readLock = lock.readLock();
        try {
            readLock.lock();
            if (list.contains(str)) {
                return str;
            }
        } finally {
            readLock.unlock();
        }
        return null;
    }
}
