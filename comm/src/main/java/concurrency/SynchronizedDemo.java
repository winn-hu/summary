package concurrency;

/**
 * ≤‚ ‘Monitor
 *
 * javap -v SynchronizedDemo.class
 */
public class SynchronizedDemo {

    public static synchronized void test() {

    }

    public static void main(String[] args) {
        synchronized (SynchronizedDemo.class) {
            System.out.println("Synchronized Demo");
        }
       test();
    }
}
