package concurrency.customThreadPool.factory;

@FunctionalInterface
public interface ThreadFacotry {

    Thread createThread(Runnable runnable);

}
