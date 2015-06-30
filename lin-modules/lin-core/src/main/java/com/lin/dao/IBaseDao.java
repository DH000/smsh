package com.lin.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
* @ClassName: IBaseDao 
* @Description: 通用DAO接口
* @author xuelin 
* @date Jun 29, 2015 2:47:18 PM 
* 
* @param <T>
* @param <PK>
 */
public interface IBaseDao<T, PK extends Serializable> {
	/**
	 * 通过id获取记录
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public T find(final Class<T> entityClass, PK id);
	
	/**
	 * 通过id获取记录
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public T load(final Class<T> entityClass, PK id);
	
	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 */
	public PK save(T entity);
	
	/**
	 * 更新或保存
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(T entity);
	
	/**
	 * 通过属性名查询
	 * 
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<T> findByProperty(final Class<T> entityClass, String propertyName, Object value);
	
	/**
	 * 通过多个属性名查询
	 * 
	 * @param entityClass
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	public List<T> findByPropertes(final Class<T> entityClass, String[] propertyNames, Object[] values);
	
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
	public List<T> findByPropertyForPage(final Class<T> entityClass, String propertyName, Object value, int offset, int length);
	
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
	public List<T> findByPropertesForPage(final Class<T> entityClass, String[] propertyNames, Object[] values, int offset, int length);
	
	/**
	 * 统计记录总数
	 * 
	 * @param entityClass
	 * @return
	 */
	public long count(final Class<T> entityClass);
	
	/**
	 * 通过某个属性统计记录总数
	 * 
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public long countByProperty(final Class<T> entityClass, String propertyName, Object value);
	
	/**
	 * 通过某个多个属性统计记录总数
	 * 
	 * @param entityClass
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	public long countByProperties(final Class<T> entityClass, String[] propertyNames, Object[] values);
}
