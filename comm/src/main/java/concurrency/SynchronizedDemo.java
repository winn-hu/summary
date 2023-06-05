package concurrency;

/**
 * ≤‚ ‘Monitor
 *
 * javap -v SynchronizedDemo.class
 */
public class SynchronizedDemo {

    public  void test(String name) {
        synchronized(SynchronizedDemo.class) {
            System.out.println(name + " Synchronized Demo");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(name + " end.");
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo demo1 = new SynchronizedDemo();
        SynchronizedDemo demo2 = new SynchronizedDemo();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo1.test("thread1");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo2.test("thrread2");
            }
        });

        thread1.start();
        thread2.start();
    }
}
