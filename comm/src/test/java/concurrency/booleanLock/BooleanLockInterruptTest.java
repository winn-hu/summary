package concurrency.booleanLock;

import java.util.concurrent.TimeUnit;

/**
 * 测试阻塞中断
 */
public class BooleanLockInterruptTest {

    private final Lock lock = new BooleanLock();

    public void syncMethod() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" get lock.");
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("BLOCKED THREAD :"+lock.getBlockedThreads());
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        BooleanLockInterruptTest test = new BooleanLockInterruptTest();

        new Thread(test::syncMethod,"t1").start();
        TimeUnit.MILLISECONDS.sleep(3);
        Thread t2 = new Thread(test::syncMethod, "t2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(3);
        t2.interrupt();
        System.out.println(t2.isInterrupted()); //true
        System.out.println(t2.getState());  //RUNNABLE
    }
}