package concurrency.returnValue;

public class JoinWait {

    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();
        thread.join();
        String returnVal = thread.returnVal;
        System.out.println(returnVal);
    }
}
