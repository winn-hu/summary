package concurrency;

/**
 * 并行一定比串行快么？
 *
 * 不一定。
 *
 * 并行需要上下文切换，设计性能消耗。
 */
public class SerialAndParallel {

    private static final long COUNT = 1000000000;

    public static void main(String[] args) throws InterruptedException {
        parallel();
        serial();
    }

    public static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < COUNT; i++) {
            a++;
        }
        int b = 0;
        for (long i = 0; i < COUNT; i++) {
            b++;
        }
        long end = System.currentTimeMillis();
        System.out.println("serial : "+ (end-start));

    }

    public static void parallel() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> {
            int a = 0;
            for (long i = 0; i < COUNT; i++) {
                a++;
            }
        });
        Thread thread2 = new Thread(() -> {
            int b = 0;
            for (long i = 0; i < COUNT; i++) {
                b++;
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        long end = System.currentTimeMillis();
        System.out.println("parallel : "+ (end-start));

    }
}
