package concurrency.threadPool.factory;

@FunctionalInterface
public interface ThreadFacotry {

    Thread createThread(Runnable runnable);

}
