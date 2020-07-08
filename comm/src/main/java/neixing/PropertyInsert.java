package neixing;

import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;


public class PropertyInsert {

	/**
	 * 获取所有的setter/getter方法
	 * @throws IntrospectionException
	 */
	@Test
	public void getAllProperty() throws IntrospectionException{
		BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
		PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : descriptors) {
			System.out.println(propertyDescriptor.getWriteMethod());
			System.out.println(propertyDescriptor.getReadMethod());
		}
	}

	/**
	 * 属性值设置和获取
	 * @throws Exception
	 */
	@Test
	public void testProperty() throws Exception{
		Person p  = new Person();
		
		//属性描述器
		PropertyDescriptor desc = new PropertyDescriptor("name", Person.class);
		//相当于set方法
		Method write = desc.getWriteMethod();
		write.invoke(p, "lisi");
		//相当于get方法
		Method read = desc.getReadMethod();
		System.out.println(read.invoke(p, (Object[]) null));
	}

}
