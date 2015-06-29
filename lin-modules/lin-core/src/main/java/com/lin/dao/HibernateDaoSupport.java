package com.lin.dao;

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
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public StringBuilder getHqlSelect(final Class<?> entityClass){
		if(null == entityClass){
			throw new IllegalArgumentException("实体类不能为null");
		}
		
		StringBuilder hqlBuf = new StringBuilder("select new ");
		hqlBuf.append(entityClass.getName()).append(" form ").append(entityClass.getName()).append(" ");
		
		return hqlBuf;
	}
}
