package concurrency.booleanLock;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface Lock {

    void lock() throws InterruptedException;

    /**
     * 指定获取锁的超时时间
     * @param mills 等待获取锁的最大时间
     * @throws InterruptedException
     * @throws TimeoutException
     */
    void lock(long mills) throws InterruptedException, TimeoutException;

    void unlock();

    List<Thread> getBlockedThreads();
}
