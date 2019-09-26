package concurrency.readWriteLock;

public class ReadWriteLockImpl implements ReadWriteLock {

    /**
     * 定义互斥锁
     */
    private final Object MUTEX = new Object();

    /**
     * 正在进行写操作的线程数
     */
    private int writers;

    /**
     * 等待写操作的线程数
     */
    private int waitingWriters;

    /**
     * 正在进行读操作的线程数
     */
    private int readers;

    /**
     * 偏好设置
     * true: 写多读少
     * false ：读多写少
     */
    private boolean preferWrite;

    public ReadWriteLockImpl() {
        this(true);
    }

    public ReadWriteLockImpl(boolean preferWriter) {
        this.preferWrite = preferWriter;
    }

    @Override
    public Lock readLcok() {
        return new ReadLock(this);
    }

    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    @Override
    public int getWriters() {
        return this.writers;
    }

    @Override
    public int getReaders() {
        return this.readers;
    }

    @Override
    public int getWaitingWriters() {
        return this.waitingWriters;
    }

    public void insreaseWriters(){
        this.writers ++;
    }

    public void decreaseWriters(){
        this.writers --;
    }

    public void increaseReaders(){
        this.readers ++;
    }

    public void decreaseReaders(){
        this.readers --;
    }

    public void insreaseWaitingWriters(){
        this.waitingWriters ++;
    }

    public void decreaseWaitingWriters(){
        this.waitingWriters --;
    }

    public Object getMUTEX() {
        return MUTEX;
    }

    public boolean isPreferWrite() {
        return preferWrite;
    }

    public void setPreferWrite(boolean preferWrite) {
        this.preferWrite = preferWrite;
    }
}
