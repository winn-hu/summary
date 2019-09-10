package concurrency.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateThreadByCachedPool {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pool.execute(() -> System.out.println(Thread.currentThread().getName()+" is running..."));
        }
    }
}
