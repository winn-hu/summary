package concurrency.readWriteLock;

public interface ReadWriteLock {

    Lock readLcok();
    Lock writeLock();

    /**
     *获取正在执行写操作的线程数量
     */
    int getWriters();

    /**
     * 获取正在执行读操作的线程数量
     */
    int getReaders();

    /**
     * 获取正在等待获取写入锁的线程数量
     */
    int getWaitingWriters();

    /**
     * 获取锁对象
     */
    Object getMUTEX();

    /**
     * 获取偏好设置
     */
    boolean isPreferWrite();

    static ReadWriteLock readWriteLock(){
        return new ReadWriteLockImpl();
    }

    static ReadWriteLock readWriteLock(boolean preferWriter){
        return new ReadWriteLockImpl(preferWriter);
    }

}
