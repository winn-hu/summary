package concurrency.limitFlow;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class TokenBucketMap {

    //存储流量
    private static final ConcurrentMap<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();

    /**
     *
     * @param resource
     * @param pps 每秒放入令牌桶的数量
     */
    public static void createRateLimiter(String resource, double pps) {
        RateLimiter rateLimiter = rateLimiterMap.get(resource);
        if(null == rateLimiter) {
            rateLimiter = RateLimiter.create(pps);
            rateLimiterMap.putIfAbsent(resource, rateLimiter);
        }
    }

    public static void main(String[] args) {
        createRateLimiter("/getInfo",10);
        ExecutorService pool = Executors.newFixedThreadPool(20);
        IntStream.range(0, 30).forEach(i -> pool.submit(() -> {
            RateLimiter limiter = rateLimiterMap.get("/getInfo");
            if(limiter.tryAcquire()) {
                System.out.println(LocalTime.now() + " : 可以执行。"+i);
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
