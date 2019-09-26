package concurrency.readWriteLock;

public interface Lock {

    void lock() throws InterruptedException;

    void unLock();

}
