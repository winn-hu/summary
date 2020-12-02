package jdk.custom.queue;

/**
 * �������ڵ������⣺
 * 1. Ԫ��poll�꣨Ԫ��ʵ���ϲ�û�д�������ɾ����ֻ�ǽ�ͷָ������ƣ�֮���ٴ��������ݵ�ʱ����δ�ͷ��ʼ��
 * 		�ж�queue��Ч��Ԫ���Ƿ�Ϊ�գ����Ϊ�գ���ͷָ���βָ�����õ�������ǰ��
 * 
 * �Ż���
 * 1. ���Զ��������б���ȣ��ڴ������е�ʱ��û�ж��е�����Ϊ���⼯�ϣ�E�����ͣ�����ָ��ΪObject[]���ͣ�
 * 		ֻ����Ԫ�ط���ʱ������Ϊ���⼯�ϣ�E������
 * 		
 * @author HuWei
 *
 * @param <E>
 */
public class MyQueue<E> {
	
	/**
	 * The default capacity while use constructor form superClass
	 */
	private final int DEFUALT_CAPACITY = 10;
	
	/**
	 * The expending capacity while arr that is generated via constructor form superClass is full 
	 */
	private final int ADD_CAPACITY = DEFUALT_CAPACITY / 2;
	
	/**
	 * store the element
	 */
	private Object[] queue;
	
	/**
	 * the head pointer of queue
	 *it always point the first alive element
	 */
	private int front;
	
	/**
	 * the end pointer of queue
	 * it always point the last alive element
	 */
	private int end;
	
	/**
	 * the real size of queue
	 */
	private int size;
	
	/**
	 * check whether is the queue generated by constructor with initCapacity
	 */
	private boolean hasInitCapacity;
	
	/**
	 * generate a queue whose default size is DEFUALT_CAPACITY
	 */
	public MyQueue() {
		queue = new Object[DEFUALT_CAPACITY];
		hasInitCapacity = false;
		end = 0;
	}
	
	/**
	 * generate a queue whose default size is initCapacity
	 */
	public MyQueue(int initCapacity){
		queue = new Object[initCapacity];
		hasInitCapacity = true;
		end = 0;
	}
	
	/**
	 * store element to queue
	 * @param element
	 * 		the element to be stored
	 */
	public void push(E element){
		if(!hasInitCapacity && size == queue.length){
			Object[] queue2 = queue;
			queue = new Object[size + ADD_CAPACITY];
			for (int i = 0; i < queue2.length; i++) {
				queue[i] = queue2[i];
			}
		}
		end = end % queue.length;
		if(queue[end] == null) {
			queue[end++] = element;
			size++;
		}else {
			throw new IndexOutOfBoundsException("Queue full");
		}
	}
	
	/**
	 * remove the first alive element in queue
	 * while queue is queue is empty, set front and end pointer point the queue's head 
	 * 
	 * @return
	 * 		the element to be remove
	 */
	public E poll(){
		front = front % queue.length;
		E element = (E)queue[front];
		queue[front] = null;
		front++;
		size--;
		return element;
	}
	
	/**
	 * get the first alive element in queue
	 * @return
	 * 		the element to be returned
	 */
	public E peek(){
		front = front % queue.length;
		E element = (E)queue[front];
		if(isEmpty()){
			element = null;
		}
		return element;
	}
	
	/**
	 * check whether is queue empty.
	 * 
	 * @return
	 * 
	 */
	public boolean isEmpty(){
		return size == 0;
	}
	
	/**
	 * get the size of queue
	 * 
	 * @return
	 * 		the size of queue
	 */
	public int size(){
		return size;
	}
}