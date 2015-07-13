package com.lin.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lin.dao.HibernateDaoSupport;
import com.lin.dao.IBaseDao;
import com.lin.utils.Collections3;

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
		String hql = getSelectHql(entityClass).append(" and model.id = :id").toString();
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		List<T> list = query.list();

		return list.get(0);
	}
	
	/**
	 * 给数据上锁
	 * 在一个事务内有效
	 * 
	 * @param entityClass
	 * @param id
	 * @param lockMode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T findForUpdate(final Class<T> entityClass, PK id, LockMode lockMode){
		String hql = getSelectHql(entityClass).append(" and model.id = :id").toString();
		Query query = getSession().createQuery(hql);
		query.setLockMode("model", lockMode);
		query.setParameter("id", id);
		List<T> list = query.list();

		return list.get(0);
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

		String hql = getSelectHql(entityClass).append(" and model.").append(propertyName).append(" = :").append(propertyName).toString();
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
	public List<T> findByProperties(final Class<T> entityClass, String[] propertyNames, Object[] values) {
		if (null == propertyNames || 0 == propertyNames.length) {
			throw new IllegalArgumentException("属性名不能为空");
		}
		
		StringBuilder hqlBuf = getSelectHql(entityClass);
		for(String propertyName : propertyNames){
			hqlBuf.append(" and model.").append(propertyName).append(" = :").append(propertyName);
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

		String hql = getSelectHql(entityClass).append(" and model.").append(propertyName).append(" = :").append(propertyName).toString();
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
	public List<T> findByPropertiesForPage(final Class<T> entityClass, String[] propertyNames, Object[] values, int offset, int length){
		if (null == propertyNames || 0 == propertyNames.length) {
			throw new IllegalArgumentException("属性名不能为空");
		}
		
		StringBuilder hqlBuf = getSelectHql(entityClass);
		for(String propertyName : propertyNames){
			hqlBuf.append(" and model.").append(propertyName).append(" = :").append(propertyName);
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
		hqlBuf.append(" and model.").append(propertyName).append(" = :").append(propertyName);
		
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
			hqlBuf.append(" and model.").append(propertyName).append(" = :").append(propertyName);
		}
		
		Query query = getSession().createQuery(hqlBuf.toString());
		
		for(int i=0, len=propertyNames.length; i<len; i++){
			query.setParameter(propertyNames[i], values[i]);
		}
		
		return (long) query.uniqueResult();
	}
	
	/**
	 * 更新实体
	 * 
	 * @param entityClass
	 * @param entity
	 */
	@Override
	public void update(final Class<T> entityClass, T entity){
		getSession().update(entityClass.getName(), entity);
	}
	
	/**
	 * 批量更新
	 * 
	 * @param entityClass
	 * @param entities
	 */
	@Override
	public void update(final Class<T> entityClass, Collection<T> entities){
		if(Collections3.isEmpty(entities)){
			return;
		}
		
		for(T entity : entities){
			update(entityClass, entity);
		}
	}
}
















