package com.lin.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * dao支持
 * 
 * @author xuelin
 *
 * 广州房友圈网络技术有限公司
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
