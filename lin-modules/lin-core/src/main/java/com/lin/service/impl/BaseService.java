package com.lin.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.lin.dao.IBaseDao;
import com.lin.service.IBaseService;
import com.lin.utils.Reflections;

/**
 * 
 * 
 * @ClassName: BaseService
 * @Description: 通用service
 * @author xuelin
 * @date Jun 29, 2015 2:51:00 PM
 * 
 * @param <T>
 * @param <PK>
 */
public class BaseService<T, PK extends Serializable> implements IBaseService<T, PK> {
	protected Class<T> entityClass;
	@Resource
	private IBaseDao<T, PK> baseDao;

	/**
	 * 实体类类型
	 * 
	 * @return
	 */
	public Class<T> getEntityClass() {
		if (null == entityClass) {
			entityClass = Reflections.getClassGenricType(getClass());
		}

		return entityClass;
	}

	/**
	 * 通过id获取记录
	 * 
	 * @param id
	 *            表唯一标识
	 * @return
	 */
	@Override
	public T find(PK id) {
		if (null == id) {
			throw new IllegalArgumentException("id不能为null");
		}

		return baseDao.find(getEntityClass(), id);
	}

	/**
	 * 通过id获取记录
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public T load(PK id) {
		if (null == id) {
			throw new IllegalArgumentException("id不能为null");
		}

		return baseDao.load(getEntityClass(), id);
	}

	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public PK save(T entity) {
		if (null == entity) {
			throw new IllegalArgumentException("entity参数不能为null");
		}

		return baseDao.save(entity);
	}

	/**
	 * 更新或保存
	 * 
	 * @param entity
	 */
	@Override
	public void saveOrUpdate(T entity) {
		if (null == entity) {
			throw new IllegalArgumentException("entity参数不能为null");
		}

		baseDao.saveOrUpdate(entity);
	}

	/**
	 * 通过属性名查询
	 * 
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	@Override
	public List<T> findByProperty(String propertyName, Object value){
		return baseDao.findByProperty(getEntityClass(), propertyName, value);
	}

	/**
	 * 通过多个属性名查询
	 * 
	 * @param entityClass
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	@Override
	public List<T> findByPropertes(String[] propertyNames, Object[] values){
		return baseDao.findByPropertes(getEntityClass(), propertyNames, values);
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
	public List<T> findByPropertyForPage(String propertyName, Object value, int offset, int length){
		return baseDao.findByPropertyForPage(getEntityClass(), propertyName, value, offset, length);
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
	public List<T> findByPropertesForPage(String[] propertyNames, Object[] values, int offset, int length){
		return baseDao.findByPropertesForPage(getEntityClass(), propertyNames, values, offset, length);
	}

	/**
	 * 统计记录总数
	 * 
	 * @param entityClass
	 * @return
	 */
	@Override
	public long count(){
		return baseDao.count(getEntityClass());
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
	public long countByProperty(String propertyName, Object value){
		return baseDao.countByProperty(getEntityClass(), propertyName, value);
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
	public long countByProperties(String[] propertyNames, Object[] values){
		return baseDao.countByProperties(getEntityClass(), propertyNames, values);
	}
}
