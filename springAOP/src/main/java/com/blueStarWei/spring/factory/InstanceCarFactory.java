package com.blueStarWei.spring.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 实例工厂方法:先需要创建工厂本身，再调用工厂方法生产bean
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
	//实例工厂方法
	public Car getCar(String brand){
		return cars.get(brand);
	}
}
