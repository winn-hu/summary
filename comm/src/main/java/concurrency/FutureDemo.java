package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * �ύ Runnable ��������ɺ� Future ���󷵻� null
 * �ύ Callable���÷�������һ�� Future ʵ����ʾ�����״̬
 *
 * pool.submit()��������
 * future.get()�ᱻ������ֱ���̻߳�ȡ���
 */
public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future<String>> futures = new ArrayList<>();

        ExecutorService pool = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 5; i++) {
            final int index = i;
            Future<String> future = pool.submit(() -> Thread.currentThread().getName() + " submit " + index);
            futures.add(future);
        }

        for (Future<String> future :  futures) {
            //�ж��߳������Ƿ��Ѿ�ִ�����
            if(future.isDone()) {
                //��ȡ�߳�ִ�н��
                System.out.println(future.get());
            }else {
                System.out.println("Thread is not done.");
            }
        }
        //����̳߳ز���ϵ������һֱ����
        pool.shutdown();
    }
}
