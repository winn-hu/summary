package concurrency;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class Volatile {

    private static volatile int num = 0;

    @Test
    public void testNonAtomic() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    num++;
                }
            }).start();
        }

        Thread.sleep(2000);

        System.out.println(num);
    }

    private static AtomicInteger count = new AtomicInteger(0);

    @Test
    public void testAtomic() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    count.incrementAndGet();
                }
            }).start();
        }

        Thread.sleep(2000);

        System.out.println(count);
    }


    private static  boolean flag = false;

    @Test
    public void testVolatile() throws InterruptedException {
        new Thread(() -> {
            flag = false;
            System.out.println("Flag is modified");
        }).start();

        while (true) {
            if (flag) {
                System.out.println("Flag is true.");
                break;
            }
        }
    }

    private static int a,b,x,y;
    @Test
    public void testResort() throws InterruptedException {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            new Thread(() -> {
                b=1;
                x=a;
            }).start();
            new Thread(() -> {
                a=1;
                y=b;
            }).start();

            Thread.sleep(100);

            if(x==0 && y==0) {
                System.out.printf("第%d次，x==0 && y==0\n",i);
                break;
            }else {
                System.out.printf("第%d次，x==%d && y==%d\n",i,x,y);
            }
        }

    }
}
