package concurrency.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CreateThreadByScheduledPool {

    public static void main(String[] args) {

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
        //delay 2s excute.
        pool.schedule(() -> System.out.println(Thread.currentThread().getName()+" delays 2s "),
                2, TimeUnit.SECONDS);
        //delay 2s and every 3s excute.
        pool.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName()+" delays 2s every 3s execte"),
                2, 3, TimeUnit.SECONDS);
    }
}
