package concurrency.finalObject;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class IntegerAccumulator {

    private int init;

    public IntegerAccumulator(int init) {
        this.init = init;
    }

    public int add(int value) {
        init += value;
        return init;
    }

    public int geValue() {
        return init;
    }

    public static void main(String[] args) {
        IntegerAccumulator accumulator = new IntegerAccumulator(0);
        IntStream.range(0,3).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
                int oldVal,result;
                synchronized (IntegerAccumulator.class){
                    oldVal = accumulator.geValue();
                    result = accumulator.add(inc);
                }
                System.out.printf(Thread.currentThread().getName()+" // %d + %d = %d \n",oldVal,inc,result);
                if (oldVal + inc != result){
                    System.out.printf(Thread.currentThread().getName()+" // ERROR : %d + %d = %d \n",oldVal,inc,result);
                }
                inc++;

                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start());
    }
}
