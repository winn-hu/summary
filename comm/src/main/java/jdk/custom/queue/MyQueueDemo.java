package jdk.custom.queue;

public class MyQueueDemo {
	public static void main(String[] args) {
		MyQueue<Integer> queue = new MyQueue<>(10);

		queue.push(1);
		queue.push(2);
		queue.push(3);
		queue.push(4);
		queue.push(5);
		queue.push(6);
		queue.push(7);
		queue.push(8);
		queue.push(9);
		queue.push(10);
		queue.poll();
		queue.push(11);

		while (!queue.isEmpty()) {
			System.out.println(queue.poll());
		}
	}
}
