package com.lin.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

import com.lin.dao.HibernateDaoSupport;
import com.lin.dao.IBaseDao;

/**
 * 
* @ClassName: BaseDao 
* @Description: 通用DAO
* @author xuelin 
* @date Jun 29, 2015 2:47:56 PM 
* 
* @param <T>
* @param <PK>
 */
public class BaseDao<T, PK extends Serializable> extends HibernateDaoSupport implements IBaseDao<T, PK> {
	/**
	 * 通过id获取记录
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public T find(final Class<T> entityClass, PK id) {
		String hql = getHqlSelect(entityClass).append("where id =:id").toString();
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		List<T> list = query.list();
		
		if(!list.isEmpty()){
			return list.get(0);
		}
		
		return null;
	}
	
	/**
	 * 通过id获取记录
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T load(final Class<T> entityClass, PK id) {
		return (T) getSession().load(entityClass, id);
	}

	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PK save(T entity) {
		return (PK) getSession().save(entity);
	}

	/**
	 * 更新或保存
	 * 
	 * @param entity
	 */
	@Override
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}
}
