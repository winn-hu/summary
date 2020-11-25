package jdk.custom.list;

public class MyArrayDemo {
	public static void main(String[] args) {
		MyArrayList<String> arr = new MyArrayList<>();
		arr.add("1");
		arr.add("2");
		arr.add("3");
		arr.add("4");
		arr.add("5");
		arr.add("6");
		arr.add("7");
		arr.add("8");
		arr.add("9");
		arr.add("10");
		arr.add("11");
		arr.add("1");
		arr.add("2");
		arr.add("3");
		arr.remove(5);
		arr.add("4");
		arr.add("5");
		arr.add("6");
		arr.add("7");
		arr.add("8");
		arr.add("9");
		arr.add("10");
		arr.add("11");
		System.out.println(arr.update(2,"9"));
		System.out.println(arr.toString());
	}
}
