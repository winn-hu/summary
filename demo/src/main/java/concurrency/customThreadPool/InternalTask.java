package concurrency.customThreadPool;

import concurrency.customThreadPool.queue.RunnableQueue;
/**
 * get task from RunnableQueue, and execute task.
 */
public class InternalTask implements Runnable {

    private final RunnableQueue taskQueue;
    private volatile boolean running = true;

    public InternalTask(RunnableQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    /**
     * Stop current thread.
     * it will be invoked by concurrency.customThreadPool.pool.ThreadPool#shutdown()
     */
    public  void stop(){
        running = false;
    }

    @Override
    public void run() {
        //It will get task and execution continuely if the current thread is running & not interrupted
        while (running && !Thread.currentThread().isInterrupted()){
            try {
                Runnable task = taskQueue.get();
                //Use current thread to execute this task
                task .run();
                System.out.println(Thread.currentThread()+ "runs task ....."+task.getClass());
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }
}
