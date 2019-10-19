package concurrency.booleanLock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class BooleanLock implements Lock {

    /**
     * 记录取得锁的线程
     */
    private Thread currentThread;
    /**
     * Bollean开关，标志锁是否已经被获取
     */
    private boolean locked = false;

    private List<Thread> blockedList = new ArrayList<>();

    @Override
    public void lock()  {
        //使用同步代码块的方式获取锁
        synchronized (this) {
            Thread currentThread = Thread.currentThread();
            //当锁已经被某个线程获取，将当前线程加入阻塞队列，并使用this.wait()释放thisMonitor
            while (locked){
                try {
                    if(!blockedList.contains(currentThread)){
                        blockedList.add(currentThread);
                    }
                    this.wait();
                } catch (InterruptedException e) {
                    blockedList.remove(currentThread);
                    e.printStackTrace();
                }
            }

            blockedList.remove(currentThread);
            this.locked = true;
            this.currentThread = currentThread;
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this){
            if(mills <= 0) {//时间不合法，调用默认的lock()
                this.lock();
            } else {
                long remainingMills = mills;
                long endMills = System.currentTimeMillis() + remainingMills;
                while (locked) {
                    if (remainingMills <= 0) {//在指定的时间内未获取锁或者当前线程被其它线程唤醒，抛出异常
                        throw new TimeoutException(Thread.currentThread().getName()+" can't get lock during "+mills);
                    }
                    if(!blockedList.contains(Thread.currentThread())){
                        blockedList.add(Thread.currentThread());
                    }
                    //等待remainingMills后重新尝试获取锁
                    this.wait(remainingMills);
                    remainingMills = endMills - System.currentTimeMillis();
                }
                blockedList.remove(Thread.currentThread());
                this.locked = true;
                this.currentThread = Thread.currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if(Thread.currentThread() == currentThread) {
                this.locked = false;
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
