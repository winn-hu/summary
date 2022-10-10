package concurrency.threadLocal;

public class ThreadDemo {

    private static int num = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                num += 10;
                System.out.println(Thread.currentThread().getName()+"->"+num);
            },"Thread-"+i).start();
        }
    }
}
