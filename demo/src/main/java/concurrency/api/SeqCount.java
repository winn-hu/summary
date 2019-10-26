package concurrency.api;

/**
 * ThreadLocal Demo
 * ÿһ���̶߳����Լ��ļ���������������
 */
public class SeqCount {

    //private Integer seqCount = 0;  //��������
    private static ThreadLocal<Integer> seqCount = ThreadLocal.withInitial(() -> 0); //����������

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
