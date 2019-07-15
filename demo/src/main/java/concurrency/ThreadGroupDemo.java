package concurrency;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Copy active Threads of ThreadGroup to Array
 */
public class ThreadGroupDemo {
    public static void main(String[] args) {
        ThreadGroup group1 = new ThreadGroup("group1");
        new Thread(group1,() ->{
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println("mainGroup.activeCount() :"+mainGroup.activeCount());
        Thread[] threads = new Thread[mainGroup.activeCount()];
        int count1 = mainGroup.enumerate(threads);
        System.out.println("count1 : "+count1+" : "+Arrays.toString(threads));

        Thread[] threads2 = new Thread[mainGroup.activeCount()];
        int count2 = mainGroup.enumerate(threads2, false);
        System.out.println("count2 : "+count2+" : "+Arrays.toString(threads2));

    }
}
