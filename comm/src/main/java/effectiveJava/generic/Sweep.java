package effectiveJava.generic;

import java.util.List;

public class Sweep {

    public static void sweep(List<?> list, int i, int j) {
        sweeper(list,i,j);
    }

    public static <E> void sweeper(List<E> list, int i, int j) {
        list.set(i, list.set(j,list.get(i)));
    }
}
