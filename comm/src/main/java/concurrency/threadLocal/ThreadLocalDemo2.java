package concurrency.threadLocal;

public class ThreadLocalDemo2 {

    private static Index index = new Index();

    private static ThreadLocal<Index> local = ThreadLocal.withInitial(() -> index);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                Index index = local.get();
                index.increase();
                System.out.println(Thread.currentThread().getName()+"->"+index.num);
            },"Thread-"+i).start();
        }
    }
}

class Index {
    int num;

    public void increase() {
         num++;
    }
}
