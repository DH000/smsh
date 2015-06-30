package com.lin.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 
* @ClassName: HibernateDaoSupport 
* @Description: DAO支持
* @author xuelin 
* @date Jun 29, 2015 2:45:10 PM 
*
 */
public class HibernateDaoSupport {
	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 获取当前session
	 * 
	 * @return
	 */
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public StringBuilder getHqlSelect(final Class<?> entityClass){
		if(null == entityClass){
			throw new IllegalArgumentException("实体类不能为null");
		}
		
		StringBuilder hqlBuf = new StringBuilder("select ");
		hqlBuf.append(" mod from ").append(entityClass.getSimpleName()).append(" as mod ");
		
		return hqlBuf;
	}
	
	public StringBuilder getHqlSelect(final Class<?> entityClass, String[] properties){
		if(null == entityClass){
			throw new IllegalArgumentException("实体类不能为null");
		}
		
		int len = properties.length;
		if(null == properties || 0 == len){
			return getHqlSelect(entityClass);
		}
		
		StringBuilder hqlBuf = new StringBuilder("select");
		for(int i=0; i<len; i++){
			hqlBuf.append(" mod.").append(properties[i]).append(",");
		}
		
		hqlBuf.deleteCharAt(hqlBuf.length() - 1);
		hqlBuf.append(" from ").append(entityClass.getSimpleName()).append(" as mod ");
		
		return hqlBuf;
	}
}
