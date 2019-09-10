package concurrency.customThreadPool.pool;

public interface ThreadPool {

    void execute(Runnable runnable);

    void shutdown();

    int getInitSize();

    int getMaxSize();

    int getCoreZize();

    int getRetainTaskSize();

    int getActiveCount();

    boolean isShutdown();
}
