package neixing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class BeanUtilsDemo {
	public static void main(String[] args) {
		try {
			ConvertUtils.register(new Converter() {	
				@Override
				public Object convert(Class type, Object value) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date date = null;
					try {
						date = format.parse((String)value);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return date;
				}
			}, Date.class);
			//本质上依赖setter/getter方法
			Person p = new Person();
			BeanUtils.setProperty(p, "name", "HuWei");
			BeanUtils.setProperty(p, "age", 24);
			BeanUtils.setProperty(p, "birthday", "1992-12-03");
			
			System.out.println(p);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
