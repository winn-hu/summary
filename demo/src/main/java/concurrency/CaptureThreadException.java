package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * UncaughtException Demo
 */
public class CaptureThreadException {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t,e) ->{
            System.out.println(t.getName()+" occur exception. ");
            e.printStackTrace();
        });

        new Thread(() ->{
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1/0);
        }).start();
    }
}
