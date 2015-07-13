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
	
	/**
	 * 返回select hql语句
	 * @param entityClass
	 * @return
	 */
	public static StringBuilder getSelectHql(final Class<?> entityClass){
		StringBuilder hqlBuf = new StringBuilder("select ");
		hqlBuf.append(" model from ").append(entityClass.getSimpleName()).append(" as model where 1=1");
		
		return hqlBuf;
	}
	
	/**
	 * 返回指定属性select hql语句
	 * 
	 * @param entityClass
	 * @param properties
	 * @return
	 */
	public static StringBuilder getSelectHql(final Class<?> entityClass, String[] properties){
		int len = properties.length;
		if(null == properties || 0 == len){
			return getSelectHql(entityClass);
		}
		
		StringBuilder hqlBuf = new StringBuilder("select");
		for(int i=0; i<len; i++){
			hqlBuf.append(" model.").append(properties[i]).append(",");
		}
		
		hqlBuf.deleteCharAt(hqlBuf.length() - 1);
		hqlBuf.append(" from ").append(entityClass.getSimpleName()).append(" as model where 1=1");
		
		return hqlBuf;
	}
	
	/**
	 * 返回count hql语句
	 * 
	 * @param entityClass
	 * @return
	 */
	public static StringBuilder getCountHql(final Class<?> entityClass){
		StringBuilder hqlBuf = new StringBuilder();
		return hqlBuf.append("select count(*) from ").append(entityClass.getSimpleName()).append(" as model where 1=1");
	}
}
