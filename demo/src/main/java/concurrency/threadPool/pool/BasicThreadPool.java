package concurrency.threadPool.pool;

import concurrency.threadPool.InternalTask;
import concurrency.threadPool.factory.ThreadFacotry;
import concurrency.threadPool.policy.DenyPolicy;
import concurrency.threadPool.queue.LinkedRunnableQueue;
import concurrency.threadPool.queue.RunnableQueue;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * BasicThreadPool is subclass of Thread, it will start when inited;
 */
public class BasicThreadPool extends Thread implements ThreadPool {

    /**
     * The inital size of thread.
     */
    private final int initSize;

    /**
     * The max size of thread.
     */
    private final int maxSize;

    /**
     * The size of core thread.
     */
    private final int coreSize;

    /**
     * The size of active thread.
     */
    private int activeCount;

    /**
     * used to crate thread
     */
    private final ThreadFacotry facotry;

    /**
     * Task queue
     */
    private final RunnableQueue taskQueue;

    /**
     * The falg to judge thread pool is shuted down.
     */
    private volatile boolean isShutdown = false;

    /**
     * Runnning thread queue
     */
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();

    /**
     * The default deny policy
     */
    private static final DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();

    /**
     * The default thread factory
     */
    private static final ThreadFacotry DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    /**
     * The active time of thread.
     */
    private final int keepActiveTime;

    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
        this(initSize,maxSize,coreSize,DEFAULT_THREAD_FACTORY,queueSize, DEFAULT_DENY_POLICY,100);
    }

    public BasicThreadPool(int initSize, int maxSize, int coreSize, ThreadFacotry facotry,
                           int queueSize, DenyPolicy denyPolicy, int keepActiveTime) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.facotry = facotry;
        this.taskQueue = new LinkedRunnableQueue(queueSize,denyPolicy,this);
        this.keepActiveTime = keepActiveTime;
        this.init();
    }

    private void init() {
        //run thread pool.
        this.start();
        //create threads.
        for (int i = 0; i < this.initSize; i++) {
            newThread();
        }
    }

    private void newThread() {
        InternalTask internalTask = new InternalTask(taskQueue);
        Thread thread = this.facotry.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        this.threadQueue.offer(threadTask);
        this.activeCount++;
        thread.start();
    }

    private void removeThread(){
        ThreadTask removeThread = threadQueue.remove();
        System.out.println("close thread : "+removeThread);
        removeThread.internalTask.stop();
        this.activeCount--;
    }

    /**
     * just put runnable to queue
     * We just need to put task into task queue, it will execute automatically
     * @param task  The task executed.
     */
    @Override
    public void execute(Runnable task) {
        if(isShutdown) throw new IllegalStateException("Yhe thread pool has been destroyed.");
        taskQueue.put(task);
    }

    /**
     * shut down thread pool.
     */
    @Override
    public void shutdown() {
        if(!isShutdown){
            isShutdown = true;
            threadQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });
            this.interrupt();
        }
    }

    @Override
    public int getInitSize() {
        checkIsShutdown();
        return initSize;
    }

    @Override
    public int getMaxSize() {
        checkIsShutdown();
        return maxSize;
    }

    @Override
    public int getCoreZize() {
        checkIsShutdown();
        return coreSize;
    }

    @Override
    public int getRetainTaskSize() {
        checkIsShutdown();
        return taskQueue.size();
    }

    @Override
    public int getActiveCount() {
        synchronized (this) {
            return activeCount;
        }
    }

    @Override
    public boolean isShutdown() {
        return isShutdown;
    }

    /**
     * override Thread.run()
     * Used to maintain the size of thread.
     */
    @Override
    public void run() {
        while (!isShutdown && !isInterrupted()) {
            try {
                TimeUnit.MILLISECONDS.sleep(keepActiveTime);
            } catch (InterruptedException e) {
                isShutdown = true;
                break;
            }
            synchronized (this) {
                if (isShutdown) break;
                if(taskQueue.size() > 0){//some tasks still aren't executed, to resize threadPool
                    if(activeCount < coreSize) {
                        for (int i = initSize; i < coreSize; i++) {
                            newThread();
                        }
                    }else if(activeCount < maxSize) {
                        for (int i = coreSize; i < maxSize; i++) {
                            newThread();
                        }
                    }
                }
                //All tasks have been finished, so remove some threads.
                if(taskQueue.size() == 0 && activeCount > coreSize){
                    for (int i = coreSize; i < activeCount; i++) {
                        removeThread();
                    }
                }

            }
        }
    }

    private void checkIsShutdown(){
        if(isShutdown) throw new IllegalStateException("The thread pool is shutted down.");
    }

    /**
     * The ThreadTask is only a combination for Thread & InternalTask
     */
    private static class ThreadTask {
        private Thread thread;
        private InternalTask internalTask;
        public ThreadTask(Thread thread, InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }

    private static class DefaultThreadFactory implements ThreadFacotry {

        private static  final AtomicInteger GROUP_COUNT = new AtomicInteger(1);
        private static final ThreadGroup group = new ThreadGroup("MyTheadPool-"+GROUP_COUNT.getAndDecrement());
        private static final AtomicInteger COUNTER = new AtomicInteger(0);

        @Override
        public Thread createThread(Runnable runnable) {
            String name = "Thread-"+COUNTER.getAndIncrement();
            System.out.println("create thread : "+name);
            return new Thread(group,runnable,name);
        }
    }
}
