package concurrency.attributes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExceptionResolve implements Runnable{

    @Override
    public void run() {
        int i = 1/0;
        System.out.println("Endind?");
    }

    public static void main(String[] args) {
        //2. 为所有的线程设置“异常处理器”
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        //3. 创建并执行线程
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(new ThreadExceptionResolve());
    }
}

//1. 定义符合线程异常处理器规范的“异常处理器”
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("The acculation is error.");
    }
}
