package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * hook thread will be executed before VM exits.
 */
public class HookThread {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
            try {
                System.out.println("The hook thread 1 is running.");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The hook thread 1`will exit.");
        }));

        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
            try {
                System.out.println("The hook thread 2 is running.");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The hook thread 2`will exit.");
        }));
    }
}
