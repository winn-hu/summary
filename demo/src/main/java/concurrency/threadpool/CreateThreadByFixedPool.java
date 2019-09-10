package concurrency.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateThreadByFixedPool {

    /**
     * Cover Runnable.run()
     */
    private static void run(){
        System.out.println(Thread.currentThread().getName()+" is running...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            pool.execute(CreateThreadByFixedPool::run);
        }
    }
}
