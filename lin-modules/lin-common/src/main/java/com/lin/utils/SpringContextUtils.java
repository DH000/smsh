package com.lin.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @ClassName: SpringContextUtils 
 * @Description: 获取speing bean
 * @author xuelin 
 * @date Jul 22, 2015 12:00:05 PM 
 *
 */
public class SpringContextUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtils.applicationContext = applicationContext;
	}

	/**
	 * 获取bean对象
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name){
		return (T) applicationContext.getBean(name);
	}
	
	/**
	 * 获取bean对象
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name, Class<T> requireType){
		return (T) applicationContext.getBean(name);
	}
	
	/**
	 * 判断是否包含bean
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isContainsBean(String name){
		return applicationContext.containsBean(name);
	}
	
	/**
	 * 判断是否是单例
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isSingleton(String name){
		return applicationContext.isSingleton(name);
	}
	
	/**
	 * 获取bean类型
	 * 
	 * @param name
	 * @return
	 */
	public static Class<?> getType(String name){
		return applicationContext.getType(name);
	}
	
	/**
	 * 获取bean别名
	 * 
	 * @param name
	 * @return
	 */
	public static String[] getAliases(String name){
		return applicationContext.getAliases(name);
	}
}
