package jdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShutDownTest {

    /**
     * ʹ���̳߳ش�������
     */
    public static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String [] args) {

        //������5���߳���Ҫִ������
        for (int i = 0 ; i < 5 ; i++) {
            final int id = i;
            Thread taski = new Thread (() -> {
                System.out.println(System.currentTimeMillis() + " : thread_"+ id + " start...");
                try {
                    TimeUnit.SECONDS.sleep(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + " : thread_"+ id + " finish!");
            });
            taski.setDaemon(true );
            executorService.submit(taski);
        }
    
        // ���һ�����Ӵ���δ������
        Runtime.getRuntime().addShutdownHook(
                new Thread (() -> {
                    System.out.println(System.currentTimeMillis() + " : "+ Thread.currentThread().getName() + " No1 shutdown hooking...");
                    try {
                        executorService.shutdown();
                        System.out.println(System.currentTimeMillis() + " : "+ Thread.currentThread().getName() + " shutdown signal got, wait threadPool finish.");
                        executorService.awaitTermination(1500 , TimeUnit.SECONDS);
                        System.out.println(System.currentTimeMillis() + " : "+ Thread.currentThread().getName() + " all thread's done.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(System.currentTimeMillis() + " : "+ Thread.currentThread().getName() + " No1 shutdown done...");
                }));
    
        // ����رչ��Ӳ���ִ��
        Runtime.getRuntime().addShutdownHook(
                new Thread (() -> {
                    try {
                        System.out.println(System.currentTimeMillis() + " : "+ Thread.currentThread().getName() + " No2 shutdown hooking...");
                        Thread.sleep(1000 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(System.currentTimeMillis() + " : "+ Thread.currentThread().getName() + " No2 shutdown done...");
                }));

        System.out.println("main method exit...");
        
        // �������jvm�˳�������͹ر��źţ�������������� jvm ��ȴ����һ�����ػ��̹߳رղŻ��˳�
        System.exit (0 );
    }

}
