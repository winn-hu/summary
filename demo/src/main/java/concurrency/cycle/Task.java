package concurrency.cycle;

@FunctionalInterface
public interface Task<T> {

    //Effect is same as Runnable
    T call();

}
