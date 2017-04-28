package com.baashome.util.httpService;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ConversionUtil {

	/**
	 * Converts a map to a JavaBean.
	 */
	public static <T extends Object> T Map2Bean(Map<String, Object> map, Class<T> clazz) throws Exception {
		T instance = clazz.newInstance();
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : descriptors) {
			String key = property.getName();
			if (map.containsKey(key)) {
				Object value = map.get(key);
				Method setter = property.getWriteMethod();
				setter.invoke(instance, value);
			}
		}
		return instance;
	}

	/**
	 * Converts a JavaBean to a map.
	 */
	public static Map<String, Object> Bean2Map(Object obj) throws Exception {
		// obj为空，结束方法
		if (obj == null)
			return null;
		Map<String, Object> map = new HashMap<String, Object>();
		/*
		 * Introspector 类为通过工具学习有关受目标 Java Bean 支持的属性、事件和方法的知识提供了一个标准方法。
		 * java的自省机制
		 */
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] ps = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : ps) {
			String key = propertyDescriptor.getName();

			if (!"class".equals(key)) {
				Method getter = propertyDescriptor.getReadMethod();
				Object value = getter.invoke(obj);
				map.put(key, value);
			}
		}
		return map;

	}

}
