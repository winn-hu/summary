package jdk.custom.list;

/**
 * 
 * show all fields and method,please click "ctrl + o"
 * 
 * 
 * �����������������⣺
 * 1. �����±꣨index��δ����Խ�����
 * 			һ��ҪУ��ָ���Ƿ�Խ��
 * 2. �������ľ��������չ�µĿռ�
 * 		����һ��������hasInitCapacity���������ж�����Ĵ�����ʽ��
 * 		�����ͨ���޲ι��췽ʽ������hasInitCapacity = false����ÿ����չ������ʱ����ԭ�����鳤�ȵĻ�����������չ���ȡ�
 * 
 * @author HuWei
 *
 * @param <E>
 */
public class MyArrayList<E> {
	/**
	 * The default capacity while use constructor form superClass
	 */
	private final int DEFUALT_CAPACITY = 10;
	
	/**
	 * The expending capacity while arr that is generated via constructor form superClass is full 
	 */
	private final int ADD_CAPACITY = DEFUALT_CAPACITY / 2;
	
	/**
	 * The array to store elements
	 */
	private E[] arr;
	
	/**
	 * The length of element to be stored into arr
	 */
	private int size;
	
	/**
	 * check whether is the queue generated by constructor with initCapacity
	 */
	private boolean hasInitCapacity;
	
	public MyArrayList() {
		arr = (E[]) new Object[DEFUALT_CAPACITY];
		hasInitCapacity = false;
	}
	
	public MyArrayList(int initCapacity){
		arr = (E[]) new Object[initCapacity];
		hasInitCapacity = true;
	}
	
	/**
	 * 
	 * @return
	 * 		the length of array
	 */
	public int size() {
		return size;
	}

	/**
	 * let element insert to arr
	 * if MyArrayList is generated via constructor form superClass and the capacity is full,then expand capacity
	 * @param element   
	 * 		the element to insert
	 * @return index    
	 * 		the index of the element to insert
	 */
	public int add(E element){
		//expend capacity
		if(!hasInitCapacity && size == arr.length){
			E[] arr2 = arr;
			arr = (E[]) new Object[arr.length + ADD_CAPACITY];
			for (int i = 0; i < arr2.length; i++) {
				arr[i] = arr2[i];
			}
		}
		arr[size] = element;
		return size++;
	}
	
	/**
	 * 
	 * @param index
	 * 		the index of the element to replace
	 * @param element    
	 * 		the element to replace
	 * @return
	 * 		the element to be replaced
	 */
	public E update(int index, E element){
		checkArange(index);
		E old = arr[index];
		arr[index] = element;
		return old;
	}
	
	/**
	 * 
	 * @param index
	 * 		the index of the element to be removed
	 * @return
	 * 		the element to be removed
	 */
	public E remove(int index){
		checkArange(index);
		
		E element = arr[index];
		if(index == size - 1){
			arr[index] = null;
		}
		for (int i = index; i < size; i++) {
			arr[i] = arr[i+1];
		}
		size--;
		return element;
	}
	
	/**
	 * 
	 * @param index
	 * 		the index of the element to get
	 * @return
	 * 		the element to get
	 */
	public E get(int index){
		return arr[index];
	}
	
	/**
	 * check whether index is out of bounds
	 * @param index
	 */
	private void checkArange(int index){
		if(index < 0 || index >= size){
			throw new ArrayIndexOutOfBoundsException();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("[");
		for (int i = 0; i < size; i++) {
			result.append(arr[i]);
			if(i != size - 1){
				result.append(",");
			}
		}
		result.append("]");
		return result.toString();
		
	}
	
}