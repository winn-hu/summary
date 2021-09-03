package concurrency;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyInteruptibly {

    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        thread1.start();
        thread2.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("================================");
        thread2.interrupt();
    }

}

class MyThread extends Thread {
    private static Lock lock = new ReentrantLock();

    @Override
    public void run() {
        String thread = Thread.currentThread().getName();
        try {
            lock.lockInterruptibly();
            System.out.println(thread+" get lock.");
            TimeUnit.HOURS.sleep(12);
        } catch (InterruptedException e) {
            System.out.println(thread+" is interrupted.");
        } finally {
            System.out.println(thread+" colse lock.");
            lock.unlock();
        }
    }
}
