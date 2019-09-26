package concurrency.readWriteLock;

public class ReadLock implements Lock {

    private final ReadWriteLockImpl readWriteLock;

    public ReadLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        Object mutex = readWriteLock.getMUTEX();
        synchronized (mutex){
            while (readWriteLock.getWaitingWriters() > 0
                    || (readWriteLock.isPreferWrite() && readWriteLock.getWaitingWriters() > 0)){
                mutex.wait();
            }
            readWriteLock.increaseReaders();
        }
    }

    @Override
    public void unLock() {
        Object mutex = readWriteLock.getMUTEX();
        synchronized (mutex){
            readWriteLock.decreaseReaders();
            mutex.notifyAll();
        }
    }
}
