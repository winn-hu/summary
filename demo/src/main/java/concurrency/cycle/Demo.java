package concurrency.cycle;

import java.util.concurrent.TimeUnit;

public class Demo {
    public static void main(String[] args) {
        Observable observableThread = new ObservableThread<>(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finish Done.");
            return "Hello Result";
        });

        observableThread.start();
    }
}
