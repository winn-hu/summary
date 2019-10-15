package concurrency.attributes;

public class ThreadGroupDemo {

    public static void print(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getThreadGroup().getName()+"-"+thread.getName());
    }

    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("Print Group");
        new Thread(group, ThreadGroupDemo::print, "t1").start();
        new Thread(group, ThreadGroupDemo::print, "t2").start();
        group.list();

    }
}
