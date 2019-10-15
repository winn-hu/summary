package jdK8;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StreamDemo {

    @Test
    public void testSum(){
        int[] array = {1,2,3,4,2};

        System.out.println("distinct => "+ Arrays.toString(IntStream.of(array).distinct().toArray()));
        System.out.println("sum => "+IntStream.of(array).sum());

        /**
         * 对元素做替换
         */
        int[] ints = IntStream.of(array).map(i -> {
            if (i == 2) {
                i = 10;
            }
            return i;
        }).toArray();
        System.out.println("sum => "+Arrays.toString(ints));
    }
}
