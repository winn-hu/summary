package concurrency.returnValue;


public class WaitSubThread {

    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();
        while (thread.returnVal == null || "".equals(thread.returnVal)) {
            System.out.println("waiting.....");
            Thread.sleep(1000);
        }
        String returnVal = thread.returnVal;
        System.out.println(returnVal);
    }
}

class MyThread extends Thread {

    String returnVal = "";
    @Override
    public void run() {
        returnVal =  "Return Value";
    }
}

