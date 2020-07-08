package neixing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class IntrospectorDemo {
	
	public static Object getInstance() throws Exception{
		try(BufferedReader in = new BufferedReader(new FileReader("obj.txt"))){
			//读取类名
			String className = in.readLine();
			//创建实例
			Class clazz = Class.forName(className);
			Constructor cons = clazz.getConstructor((Class<?>[]) null);
			Object o = cons.newInstance((Object[]) null);
			
			//读取属性
			String line;
			while((line =in.readLine()) != null){
				String[] datas = line.split("=");
				Field field = clazz.getDeclaredField(datas[0]);
				if(field.getType() == int.class){
					field.set(o, Integer.valueOf(datas[1]));					
				}else{
					field.set(o, datas[1]);	
				}
			}
			return o;
		}
	}
	
	public static void main(String[] args) {
		Person p = null;
		try {
			p = (Person) getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(p);
	}
}
