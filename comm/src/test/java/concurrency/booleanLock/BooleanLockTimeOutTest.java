package concurrency.booleanLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 测试超时
 */
public class BooleanLockTimeOutTest {

    private final Lock lock = new BooleanLock();

    public void syncTimeOutMethod() {
        try {
            lock.lock(1000);
            System.out.println(Thread.currentThread().getName()+" get lock.");
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    
    public static void main(String[] args) throws InterruptedException {
        BooleanLockTimeOutTest test = new BooleanLockTimeOutTest();

        new Thread(test::syncTimeOutMethod,"t1").start();
        TimeUnit.MILLISECONDS.sleep(3);
        new Thread(test::syncTimeOutMethod, "t2").start();

    }
}