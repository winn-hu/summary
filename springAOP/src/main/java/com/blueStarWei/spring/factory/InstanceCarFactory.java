package com.blueStarWei.spring.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * ʵ����������:����Ҫ�������������ٵ��ù�����������bean
 * @author lenovo
 *
 */
public class InstanceCarFactory {
	private Map<String,Car> cars = null;
	
	public InstanceCarFactory(){
		cars = new HashMap<String,Car>();
		cars.put("audi", new Car("audi",300000));
		cars.put("ford", new Car("ford",200000));
	}
	//ʵ����������
	public Car getCar(String brand){
		return cars.get(brand);
	}
}
