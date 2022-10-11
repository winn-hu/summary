package concurrency.returnValue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask提供了对Future的基本实现, 是一个可取消的异步计算，支持取消和获取结果
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask<>(new Test());
        Thread thread = new Thread(futureTask);
        thread.start();
        Object returnVal = futureTask.get();
        System.out.println(returnVal);
    }
}

class Test implements Callable<String> {

    @Override
    public String call() {
        return "Return Value...";
    }
}
