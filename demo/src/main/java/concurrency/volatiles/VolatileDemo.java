package concurrency.volatiles;

import java.util.concurrent.TimeUnit;

public class VolatileDemo {

    private final static int MAX = 5;
    //private static int intit_value = 0;
    private static volatile int intit_value = 0;

    public static void main(String[] args) {
        new Thread(() ->{
            int curVal = intit_value;
            while (curVal < MAX){
                if (intit_value != curVal){
                    System.out.println("Reader : The init_value has been updated to "+curVal);
                    curVal = intit_value;
                }
            }
        },"Reader").start();

        new Thread(() -> {
            int curVal = intit_value;
            while (curVal < MAX){
                System.out.println("Writer : The init_value has been updated to "+(++curVal));
                intit_value = curVal;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Writer").start();
    }
}
