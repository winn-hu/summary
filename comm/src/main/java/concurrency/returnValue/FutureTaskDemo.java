package concurrency.returnValue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask�ṩ�˶�Future�Ļ���ʵ��, ��һ����ȡ�����첽���㣬֧��ȡ���ͻ�ȡ���
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new Test());
        Thread thread = new Thread(futureTask);
        thread.start();
        Object returnVal = futureTask.get();
        System.out.println(returnVal);
    }
}

class Test implements Callable {
    @Override
    public Object call() {
        return "Return Value...";
    }
}
