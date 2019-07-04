package concurrency.booleanLock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class BooleanLock implements Lock {

    private Thread currentThread;
    private boolean locked = false;
    private List<Thread> blockedList = new ArrayList<>();

    @Override
    public void lock()  {
        synchronized (this) {
            Thread currentThread = Thread.currentThread();
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
        if(mills <= 0) {
            this.lock();
        } else {
            long remainingMills = mills;
            long endMills = System.currentTimeMillis() + remainingMills;
            while (locked) {
                if (remainingMills <= 0) {
                    throw new TimeoutException(Thread.currentThread().getName()+" can't get lock during "+mills);
                }
                if(!blockedList.contains(Thread.currentThread())){
                    blockedList.add(Thread.currentThread());
                }
                this.wait(remainingMills);
                remainingMills = endMills - System.currentTimeMillis();
            }
            blockedList.remove(Thread.currentThread());
            this.locked = true;
            this.currentThread = Thread.currentThread();
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
