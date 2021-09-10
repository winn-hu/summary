package concurrency;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void read() {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        readLock.lock();
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis() - start <= 1) {
            System.out.println(Thread.currentThread().getName()+" is reading.");
        }
        readLock.unlock();
        System.out.println(Thread.currentThread().getName()+" readed.");
    }

    public static void write() {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis() - start <= 0.1) {
            System.out.println(Thread.currentThread().getName()+" is writing.");
        }
        writeLock.unlock();
        System.out.println(Thread.currentThread().getName()+" writed.");
    }

    public static void main(String[] args) {
        new Thread(() -> {
            read();
            write();
        }).start();

        new Thread(() -> {
            read();
            write();
        }).start();
    }
}
