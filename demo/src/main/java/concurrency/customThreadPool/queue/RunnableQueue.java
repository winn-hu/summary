package concurrency.customThreadPool.queue;

/**
 * Task Queue
 */
public interface RunnableQueue {

    void put(Runnable runnable);

    Runnable get() throws InterruptedException;

    int size();
}
