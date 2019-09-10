package concurrency.customThreadPool.test;

import concurrency.customThreadPool.pool.BasicThreadPool;

import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        BasicThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    System.out.println(Thread.currentThread()+" do something...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        while (true) {
            System.out.println("Active Thread Count : " + threadPool.getActiveCount());
            System.out.println("Quene Size : " + threadPool.getRetainTaskSize());
            System.out.println("===========================");
            TimeUnit.MILLISECONDS.sleep(100);

        }
    }
}
