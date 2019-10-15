package concurrency.attributes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UncaughtExceptionDemo implements Runnable{

    @Override
    public void run() {
        int i = 1/0;
        System.out.println("Endind?");
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(new UncaughtExceptionDemo());
    }
}
