package com.lin.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lin.dao.HibernateDaoSupport;
import com.lin.dao.BaseDao;
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
public class BaseDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements BaseDao<T, PK> {
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
	 * 分页查询
	 * 
	 * @param entityClass
	 * @param offset
	 * @param length
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findForPage(final Class<T> entityClass, Integer offset, Integer length){
		String hql = getSelectHql(entityClass).toString();
		Query query = getSession().createQuery(hql);
		List<T> list = query.list();
		
		if(null != offset){
			query.setFirstResult(offset);
		}
		if(null != length){
			query.setMaxResults(length);
		}

		return list;
	}

	/**
	 * 给数据上锁 在一个事务内有效
	 * 
	 * @param entityClass
	 * @param id
	 * @param lockMode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T findForUpdate(final Class<T> entityClass, PK id, LockMode lockMode) {
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
		StringBuilder hqlBuf = getSelectHql(entityClass);
		for (String propertyName : propertyNames) {
			hqlBuf.append(" and model.").append(propertyName).append(" = :").append(propertyName);
		}

		Query query = getSession().createQuery(hqlBuf.toString());
		for (int i = 0, len = propertyNames.length; i < len; i++) {
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
	public List<T> findByPropertyForPage(final Class<T> entityClass, String propertyName, Object value, Integer offset, Integer length) {
		String hql = getSelectHql(entityClass).append(" and model.").append(propertyName).append(" = :").append(propertyName).toString();
		Query query = getSession().createQuery(hql);
		query.setParameter(propertyName, value);

		if(null != offset){
			query.setFirstResult(offset);
		}
		if(null != length){
			query.setMaxResults(length);
		}

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
	public List<T> findByPropertiesForPage(final Class<T> entityClass, String[] propertyNames, Object[] values, Integer offset, Integer length) {
		StringBuilder hqlBuf = getSelectHql(entityClass);
		for (String propertyName : propertyNames) {
			hqlBuf.append(" and model.").append(propertyName).append(" = :").append(propertyName);
		}

		Query query = getSession().createQuery(hqlBuf.toString());
		for (int i = 0, len = propertyNames.length; i < len; i++) {
			query.setParameter(propertyNames[i], values[i]);
		}

		if(null != offset){
			query.setFirstResult(offset);
		}
		if(null != length){
			query.setMaxResults(length);
		}

		return query.list();
	}

	/**
	 * 统计记录总数
	 * 
	 * @param entityClass
	 * @return
	 */
	@Override
	public long count(final Class<T> entityClass) {
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
	public long countByProperty(final Class<T> entityClass, String propertyName, Object value) {
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
	public long countByProperties(final Class<T> entityClass, String[] propertyNames, Object[] values) {
		StringBuilder hqlBuf = getCountHql(entityClass);
		for (String propertyName : propertyNames) {
			hqlBuf.append(" and model.").append(propertyName).append(" = :").append(propertyName);
		}

		Query query = getSession().createQuery(hqlBuf.toString());

		for (int i = 0, len = propertyNames.length; i < len; i++) {
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
	public void update(final Class<T> entityClass, T entity) {
		getSession().update(entityClass.getName(), entity);
	}

	/**
	 * 批量更新
	 * 
	 * @param entityClass
	 * @param entities
	 */
	@Override
	public void update(final Class<T> entityClass, Collection<T> entities) {
		if (Collections3.isEmpty(entities)) {
			return;
		}

		for (T entity : entities) {
			update(entityClass, entity);
		}
	}

	/**
	 * 通过id获取指定属性
	 * 
	 * @param resProNames
	 *            结果字段
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> findProperties(final Class<T> entityClass, String[] resProNames, PK id) {
		StringBuilder hqlBuf = getSelectHql(entityClass, resProNames);
		hqlBuf.append(" and model.id = :id");

		Query query = getSession().createQuery(hqlBuf.toString());
		query.setParameter("id", id);
		List list = query.list();

		Map<String, Object> map = new HashMap<String, Object>();
		if (list.isEmpty()) {
			return map;
		}

		int len = resProNames.length;
		if (1 == len) {
			map.put(resProNames[0], list.get(0));
			return map;
		}

		Object[] objArr = (Object[]) list.get(0);
		for (int i = 0; i < len; i++) {
			map.put(resProNames[i], objArr[i]);
		}

		return map;
	}

	/**
	 * 获取指定属性字段
	 * 
	 * @param resProNames
	 *            结果字段
	 * @param propertyNames
	 *            参数字段
	 * @param values
	 *            参数值
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> findProperties(final Class<T> entityClass, String[] resProNames, String[] propertyNames, Object[] values) {
		StringBuilder hqlBuf = getSelectHql(entityClass, resProNames);

		for (String propertyName : propertyNames) {
			hqlBuf.append(" and model.").append(propertyName).append(" = :").append(propertyName);
		}
		Query query = getSession().createQuery(hqlBuf.toString());
		for (int i = 0, len = propertyNames.length; i < len; i++) {
			query.setParameter(propertyNames[i], values[i]);
		}

		List list = query.list();
		List<Map<String, Object>> resList = new ArrayList<>();
		if (list.isEmpty()) {
			return resList;
		}

		int len = resProNames.length;
		for (Object obj : list) {
			Map<String, Object> map = new HashMap<>();
			if (1 == len) {
				map.put(resProNames[0], obj);
			} else {
				Object[] objArr = (Object[]) obj;
				for (int i = 0; i < len; i++) {
					map.put(resProNames[i], objArr[i]);
				}
			}
			resList.add(map);
		}

		return resList;
	}

	/**
	 * 获取指定属性字段
	 * 
	 * @param resProNames
	 *            结果字段
	 * @param propertyNames
	 *            参数字段
	 * @param values
	 *            参数值
	 * @param offset
	 *            记录下标
	 * @param length
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> findPropertiesForPage(final Class<T> entityClass, String[] resProNames, String[] propertyNames, Object[] values, Integer offset, Integer length) {
		StringBuilder hqlBuf = getSelectHql(entityClass, resProNames);

		for (String propertyName : propertyNames) {
			hqlBuf.append(" and model.").append(propertyName).append(" = :").append(propertyName);
		}
		Query query = getSession().createQuery(hqlBuf.toString());
		for (int i = 0, len = propertyNames.length; i < len; i++) {
			query.setParameter(propertyNames[i], values[i]);
		}
		if(null != offset){
			query.setFirstResult(offset);
		}
		if(null != length){
			query.setMaxResults(length);
		}

		List list = query.list();
		List<Map<String, Object>> resList = new ArrayList<>();
		if (list.isEmpty()) {
			return resList;
		}

		int len = resProNames.length;
		for (Object obj : list) {
			Map<String, Object> map = new HashMap<>();
			if (1 == len) {
				map.put(resProNames[0], obj);
			} else {
				Object[] objArr = (Object[]) obj;
				for (int i = 0; i < len; i++) {
					map.put(resProNames[i], objArr[i]);
				}
			}
			resList.add(map);
		}

		return resList;
	}
	
	/**
	 * 删除
	 * 
	 * @param entity
	 */
	@Override
	public void delete(T entity){
		getSession().delete(entity);
	}
	
	/**
	 * 批量删除
	 * 
	 * @param entities
	 */
	@Override
	public void delete(Collection<T> entities){
		for(T entity : entities){
			delete(entity);
		}
	}
}
