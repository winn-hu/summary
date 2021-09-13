package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * synchronized缺陷：
 * 1）不能控制等待时长
 * 2）不能中断等待
 *
 * 使用booleanLock弥补缺陷
 */
public class SyncDefect {

    /**
     *线程休眠一个小时
     */
    public synchronized void syncMethod(){
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncDefect defect = new SyncDefect();
        new Thread(defect::syncMethod,"t1").start();

        //休眠3毫秒后启动线程t2，确保t1先进入同步方法
        TimeUnit.MILLISECONDS.sleep(3);
        Thread t2 = new Thread(defect::syncMethod, "t2");
        t2.start();

        //休眠3毫秒后中断线程t2，确保t2已经启动
        TimeUnit.MILLISECONDS.sleep(3);
        t2.interrupt();

        System.out.println(t2.isInterrupted()); //true
        System.out.println(t2.getState());  //BLOCKED
    }
}
