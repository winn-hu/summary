package concurrency.returnValue;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        LatchThread thread = new LatchThread(latch);
        thread.start();
        latch.await();
        System.out.println(thread.returnVal);
    }
}

class LatchThread extends Thread {
    private CountDownLatch latch;
    String returnVal;

    public LatchThread(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        returnVal = "Return Value...";
        latch.countDown();
    }
}
