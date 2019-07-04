package concurrency.booleanLock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class BooleanLockTest {

    private final Lock lock = new BooleanLock();

    public void syncMethod() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" get lock.");
            int random = new Random().nextInt(10);
            TimeUnit.SECONDS.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("BLOCKED THREAD :"+lock.getBlockedThreads());
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BooleanLockTest test = new BooleanLockTest();
        /*IntStream.range(0,10)
                .mapToObj(i -> new Thread(test::syncMethod))
                .forEach(Thread::start);*/

        Thread t1 = new Thread(test::syncMethod,"t1");
        t1.start();
        Thread t2 = new Thread(test::syncMethod, "t2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(8);
        t2.interrupt();
    }
}