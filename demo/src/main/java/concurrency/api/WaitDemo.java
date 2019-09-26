package concurrency.api;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitDemo {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(() -> {
            synchronized (WaitDemo.class){
                System.out.println("Enter Thread1...");
                System.out.println(Thread.currentThread().getName()+" is waiting...");
                try {
                    WaitDemo.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread1 is going...");
                System.out.println("Shut down Thread1.");
            }
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pool.execute(() ->{
            synchronized (WaitDemo.class) {
                System.out.println("Enter Thread2...");
                System.out.println(Thread.currentThread().getName()+" is notifying other thread...");
                WaitDemo.class.notify();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread2 is going...");
                System.out.println("Shut down Thread2.");
            }

        });
    }

}
