package concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Runnable
 */
public class FixedThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 5; i++) {
            pool.execute(() -> System.out.println(Thread.currentThread().getName()+" execute..."));
        }

        for (int i = 0; i < 5; i++) {
            //pool.submit()不会阻塞
            pool.submit(() -> System.out.println(Thread.currentThread().getName()+" submit..."));
        }

        for (int i = 0; i < 5; i++) {
            Future<String> future = pool.submit(() -> Thread.currentThread().getName() + " future...");
            //future.get()会被阻塞，直到线程获取结果
            System.out.println(future.get());
        }

        pool.shutdown();
    }
}
