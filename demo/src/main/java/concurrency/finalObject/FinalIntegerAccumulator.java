package concurrency.finalObject;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public final class FinalIntegerAccumulator {

    /**
     *只能在初始化时赋值
     * 初始默认值或在构造器里赋值
     */
    private final int init;

    public FinalIntegerAccumulator(int init) {
        this.init = init;
    }

    public FinalIntegerAccumulator(FinalIntegerAccumulator accumulator, int init) {
        this.init = accumulator.getValue() + init;
    }

    public FinalIntegerAccumulator add(int value) {
        return new FinalIntegerAccumulator(this,value);
    }

    public int getValue() {
        return init;
    }

    public static void main(String[] args) {
        IntStream.range(0,3).forEach(i -> new Thread(() -> {
            FinalIntegerAccumulator accumulator = new FinalIntegerAccumulator(0);
            int inc = 0;
            while (true) {
               int oldVal = accumulator.getValue();
                accumulator = accumulator.add(inc);
               int result = accumulator.getValue();
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
