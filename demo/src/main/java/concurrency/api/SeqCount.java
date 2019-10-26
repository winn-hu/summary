package concurrency.api;

/**
 * ThreadLocal Demo
 * 每一个线程都有自己的计数器，互不干扰
 */
public class SeqCount {

    //private Integer seqCount = 0;  //变量共享
    private static ThreadLocal<Integer> seqCount = ThreadLocal.withInitial(() -> 0); //变量不共享

    public int nextSeq() {
        seqCount.set(seqCount.get() + 1);
        return seqCount.get();
    }

    public static void main(String[] args) {
        SeqCount seqCount = new SeqCount();
        new SeqThread(seqCount).start();
        new SeqThread(seqCount).start();
        new SeqThread(seqCount).start();
        new SeqThread(seqCount).start();
        new SeqThread(seqCount).start();
    }

    public static class SeqThread extends Thread {

        private SeqCount seqCount;

        public SeqThread(SeqCount seqCount) {
            this.seqCount = seqCount;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName()+".seqCount = "+seqCount.nextSeq());
            }
        }
    }
}
