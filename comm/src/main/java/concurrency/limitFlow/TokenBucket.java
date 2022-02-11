package concurrency.limitFlow;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class TokenBucket {

    private static final double PERMITS_PER_SEC = 10;

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(20);
        RateLimiter limiter = RateLimiter.create(PERMITS_PER_SEC);
        IntStream.range(0, 20).forEach(i -> pool.submit(() -> {
            if(limiter.tryAcquire()) {
                System.out.println(LocalTime.now() + " : 执行。"+i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(LocalTime.now() + " : 限流。"+i);
            }

        }));
        pool.shutdown();
    }
}
