package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 提交 Runnable ，任务完成后 Future 对象返回 null
 * 提交 Callable，该方法返回一个 Future 实例表示任务的状态
 *
 * pool.submit()不会阻塞
 * future.get()会被阻塞，直到线程获取结果
 */
public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future<String>> futures = new ArrayList<>();

        ExecutorService pool = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 5; i++) {
            final int index = i;
            Future<String> future = pool.submit(() -> Thread.currentThread().getName() + " submit " + index);
            futures.add(future);
        }

        for (Future<String> future :  futures) {
            //判断线程任务是否已经执行完成
            if(future.isDone()) {
                //获取线程执行结果
                System.out.println(future.get());
            }else {
                System.out.println("Thread is not done.");
            }
        }
        //如果线程池不关系，程序将一直运行
        pool.shutdown();
    }
}
