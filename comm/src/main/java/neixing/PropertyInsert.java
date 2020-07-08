package neixing;

import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;


public class PropertyInsert {

	/**
	 * ��ȡ���е�setter/getter����
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
	 * ����ֵ���úͻ�ȡ
	 * @throws Exception
	 */
	@Test
	public void testProperty() throws Exception{
		Person p  = new Person();
		
		//����������
		PropertyDescriptor desc = new PropertyDescriptor("name", Person.class);
		//�൱��set����
		Method write = desc.getWriteMethod();
		write.invoke(p, "lisi");
		//�൱��get����
		Method read = desc.getReadMethod();
		System.out.println(read.invoke(p, (Object[]) null));
	}

}
