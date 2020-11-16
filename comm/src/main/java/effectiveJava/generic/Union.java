package effectiveJava.generic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Union {

    public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> result = new HashSet<E>(s1);
        result.addAll(s2);
        return result;
    }

    public static void main(String[] args) {
        Set<Integer> integers = new HashSet<Integer>(Arrays.asList(1,2,3,4));
        Set<Double> doubles = new HashSet<Double>(Arrays.asList(5.1,6.2,7.3));
        Set<Number> numbers = Union.<Number>union(integers,doubles);

    }
}
