package com.lin.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

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
@Repository("baseDao")
public class BaseDao<T, PK extends Serializable> extends HibernateDaoSupport implements IBaseDao<T, PK> {
	/**
	 * 通过id获取记录
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public T find(final Class<T> entityClass, PK id) {
		String hql = getSelectHql(entityClass).append(" and mod.id = :id").toString();
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		List<T> list = query.list();

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * 通过id获取记录
	 * 
	 * @param entityClass
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

	/**
	 * 通过属性名查询
	 * 
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByProperty(final Class<T> entityClass, String propertyName, Object value) {
		if (StringUtils.isBlank(propertyName)) {
			throw new IllegalArgumentException("属性名不能为空");
		}

		String hql = getSelectHql(entityClass).append(" and mod.").append(propertyName).append(" = :").append(propertyName).toString();
		Query query = getSession().createQuery(hql);
		query.setParameter(propertyName, value);
		
		return query.list();
	}

	/**
	 * 通过多个属性名查询
	 * 
	 * @param entityClass
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByPropertes(final Class<T> entityClass, String[] propertyNames, Object[] values) {
		if (null == propertyNames || 0 == propertyNames.length) {
			throw new IllegalArgumentException("属性名不能为空");
		}
		
		StringBuilder hqlBuf = getSelectHql(entityClass);
		for(String propertyName : propertyNames){
			hqlBuf.append(" and mod.").append(propertyName).append(" = :").append(propertyName);
		}
		
		Query query = getSession().createQuery(hqlBuf.toString());
		for(int i=0, len=propertyNames.length; i<len; i++){
			query.setParameter(propertyNames[i], values[i]);
		}
		
		return query.list();
	}
	
	/**
	 * 通过属性名分页查询
	 * 
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @param offset
	 * @param length
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByPropertyForPage(final Class<T> entityClass, String propertyName, Object value, int offset, int length){
		if (StringUtils.isBlank(propertyName)) {
			throw new IllegalArgumentException("属性名不能为空");
		}

		String hql = getSelectHql(entityClass).append(" and mod.").append(propertyName).append(" = :").append(propertyName).toString();
		Query query = getSession().createQuery(hql);
		query.setParameter(propertyName, value);
		
		query.setFirstResult(offset);
		query.setMaxResults(length);
		
		return query.list();
	}
	
	/**
	 * 通过多个属性名分页查询
	 * 
	 * @param entityClass
	 * @param propertyNames
	 * @param values
	 * @param offset
	 * @param length
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByPropertesForPage(final Class<T> entityClass, String[] propertyNames, Object[] values, int offset, int length){
		if (null == propertyNames || 0 == propertyNames.length) {
			throw new IllegalArgumentException("属性名不能为空");
		}
		
		StringBuilder hqlBuf = getSelectHql(entityClass);
		for(String propertyName : propertyNames){
			hqlBuf.append(" and mod.").append(propertyName).append(" = :").append(propertyName);
		}
		
		Query query = getSession().createQuery(hqlBuf.toString());
		for(int i=0, len=propertyNames.length; i<len; i++){
			query.setParameter(propertyNames[i], values[i]);
		}
		
		query.setFirstResult(offset);
		query.setMaxResults(length);
		
		return query.list();
	}
	
	/**
	 * 统计记录总数
	 * 
	 * @param entityClass
	 * @return
	 */
	@Override
	public long count(final Class<T> entityClass){
		StringBuilder hqlBuf = getCountHql(entityClass);
		
		Query query = getSession().createQuery(hqlBuf.toString());
		
		return (long) query.uniqueResult();
	}
	
	/**
	 * 通过某个属性统计记录总数
	 * 
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	@Override
	public long countByProperty(final Class<T> entityClass, String propertyName, Object value){
		if (StringUtils.isBlank(propertyName)) {
			throw new IllegalArgumentException("属性名不能为空");
		}
		
		StringBuilder hqlBuf = getCountHql(entityClass);
		hqlBuf.append(" and mod.").append(propertyName).append(" = :").append(propertyName);
		
		Query query = getSession().createQuery(hqlBuf.toString());
		query.setParameter(propertyName, value);
		
		return (long) query.uniqueResult();
	}
	
	/**
	 * 通过某个多个属性统计记录总数
	 * 
	 * @param entityClass
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	@Override
	public long countByProperties(final Class<T> entityClass, String[] propertyNames, Object[] values){
		if (null == propertyNames || 0 == propertyNames.length) {
			throw new IllegalArgumentException("属性名不能为空");
		}
		
		StringBuilder hqlBuf = getCountHql(entityClass);
		for(String propertyName : propertyNames){
			hqlBuf.append(" and mod.").append(propertyName).append(" = :").append(propertyName);
		}
		
		Query query = getSession().createQuery(hqlBuf.toString());
		
		for(int i=0, len=propertyNames.length; i<len; i++){
			query.setParameter(propertyNames[i], values[i]);
		}
		
		return (long) query.uniqueResult();
	}
}
















