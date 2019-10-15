package concurrency.attributes;

import org.junit.Test;

public class Priority {

    @Test
    public void defaultPriority(){
        int mainThreadPriority = Thread.currentThread().getPriority();
        System.out.println("default priority is "+mainThreadPriority);
    }

    @Test
    public void extendFather(){
        Thread mainThread = Thread.currentThread();
        mainThread.setPriority(4);
        int mainThreadPriority = mainThread.getPriority();
        System.out.println("main thread's priority is "+mainThreadPriority);
        Thread t1 = new Thread(() -> System.out.println(Thread.currentThread().getName()),"t1");
        System.out.println("t1 thread's priority is "+t1.getPriority());
    }
}
