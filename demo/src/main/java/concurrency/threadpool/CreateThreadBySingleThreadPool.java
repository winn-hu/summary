package concurrency.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateThreadBySingleThreadPool {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            pool.execute(() ->{
                System.out.println(String.format("The thread %d (%s) is running...",
                        index,Thread.currentThread().getName()));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
