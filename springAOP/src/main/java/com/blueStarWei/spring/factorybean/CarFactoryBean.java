package com.blueStarWei.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * �Զ����factoryBean��Ҫʵ��spring�ṩ��FactoryBean�ӿ�
 * @author lenovo
 *
 */
public class CarFactoryBean implements FactoryBean<Car> {
	private String brand;
	
	public void setBrand(String brand) {
		this.brand = brand;
	}

	//����bean����
	@Override
	public Car getObject() throws Exception {
		return new Car(brand,300000);
	}

	//����bean������
	@Override
	public Class<?> getObjectType() {
		return Car.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}


}
