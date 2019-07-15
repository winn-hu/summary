package concurrency.threadPool.queue;

import concurrency.threadPool.policy.DenyPolicy;
import concurrency.threadPool.pool.ThreadPool;

import java.util.LinkedList;

public class LinkedRunnableQueue implements RunnableQueue {

    /**
     * The max capacity of runnableList
     */
    private int limit;

    /**
     * The deny policy will be executed if runnableList's size >= limit.
     */
    private final DenyPolicy denyPolicy;

    /**
     * it is used to store task.
     */
    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    private final ThreadPool pool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool pool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.pool = pool;
    }

    @Override
    public void put(Runnable runnable) {
        synchronized (runnableList) {
            if(runnableList.size() >= limit) {
                denyPolicy.reject(runnable,pool);
            }else {
                runnableList.addLast(runnable);
                runnableList.notifyAll();
            }
        }
    }

    @Override
    public Runnable get() throws InterruptedException {
        synchronized (runnableList) {
            while (runnableList.isEmpty()){
                runnableList.wait();
            }
            return runnableList.removeFirst();
        }
    }

    @Override
    public int size() {
        synchronized (runnableList) {
            return runnableList.size();
        }
    }
}
