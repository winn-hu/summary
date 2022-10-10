package concurrency.threadLocal;

public class ThreadLocalDemo {

    private static ThreadLocal<Integer> num = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                num.set(num.get()+10);
                System.out.println(Thread.currentThread().getName()+"->"+num.get());
            },"Thread-"+i).start();
        }
    }
}
