package concurrency;

import java.util.concurrent.TimeUnit;

public class SyncDefect {

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
        TimeUnit.MILLISECONDS.sleep(3);
        Thread t2 = new Thread(defect::syncMethod, "t2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(3);
        t2.interrupt();
        System.out.println(t2.isInterrupted()); //true
        System.out.println(t2.getState());  //BLOCKED
    }
}
