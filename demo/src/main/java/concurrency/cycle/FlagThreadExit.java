package concurrency.cycle;

import java.util.concurrent.TimeUnit;

public class FlagThreadExit {

	static class MyTask extends Thread{
		
		private volatile boolean closed = false;
		
		@Override
		public void run() {
			System.out.println("I will start work.");
			while(!closed && !isInterrupted()){
				System.out.println("working....");
			}
			System.out.println("I will exit.");
		}
		
		public void closed(){
			this.closed = true;
			this.interrupt();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		MyTask task = new MyTask();
		task.start();
		TimeUnit.MICROSECONDS.sleep(100);
		System.out.println("System will exit.");
		task.closed();
	}
}
