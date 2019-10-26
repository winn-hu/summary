package jdK8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
            if(i == 2) {
                return 10;
            } else {
                return i;
            }
        }).toArray();
        System.out.println("sum => "+Arrays.toString(ints));
    }

    @Test
    public void testCollect() {
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        List<String> list1 = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(list1);
    }

    /**
     *
     */
    @Test
    public void testReduce() {
        Integer reduce = Stream.of(1, 3, 5, 7)
                .reduce(10, (count, item) ->{
                    System.out.printf("{count : item} = {%s : %s}  \n",count,item);
                    return count + item;
                } );
        System.out.println(reduce);
    }
}
