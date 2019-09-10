package concurrency.cycle;

import java.util.concurrent.TimeUnit;

public class InterruptThreadExit {

	public static void main(String[] args) throws InterruptedException {
		Thread t  = new Thread(){
			@Override
			public void run() {
				System.out.println("I will start work.");
				while(!isInterrupted()){
					System.out.println("working....");
				}
				System.out.println("I will exit.");
			}
		};
		t.start();
		TimeUnit.MICROSECONDS.sleep(100);
		System.out.println("System will exit.");
		t.interrupt();
	}
}
