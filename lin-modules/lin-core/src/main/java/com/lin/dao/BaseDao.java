package com.lin.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;

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
public interface BaseDao<T, PK extends Serializable> {
	/**
	 * 通过id获取记录
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public T find(final Class<T> entityClass, PK id);
	
	/**
	 * 分页查询
	 * 
	 * @param entityClass
	 * @param offset
	 * @param length
	 * @return
	 */
	public List<T> findForPage(final Class<T> entityClass, Integer offset, Integer length);
	
	/**
	 * 给数据上锁
	 * 在一个事务内有效
	 * 
	 * @param entityClass
	 * @param id
	 * @param lockMode
	 * @return
	 */
	public T findForUpdate(final Class<T> entityClass, PK id, LockMode lockMode);
	
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
	public List<T> findByProperties(final Class<T> entityClass, String[] propertyNames, Object[] values);
	
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
	public List<T> findByPropertyForPage(final Class<T> entityClass, String propertyName, Object value, Integer offset, Integer length);
	
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
	public List<T> findByPropertiesForPage(final Class<T> entityClass, String[] propertyNames, Object[] values, Integer offset, Integer length);
	
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
	
	/**
	 * 更新实体
	 * 
	 * @param entityClass
	 * @param entity
	 */
	public void update(final Class<T> entityClass, T entity);
	
	/**
	 * 批量更新
	 * 
	 * @param entityClass
	 * @param entities
	 */
	public void update(final Class<T> entityClass, Collection<T> entities);
	
	/**
	 * 通过id获取指定属性
	 * 
	 * @param resProNames	结果字段
	 * @param id
	 * @return
	 */
	public Map<String, Object> findProperties(final Class<T> entityClass, String[] resProNames, PK id);
	
	/**
	 * 获取指定属性字段
	 * 
	 * @param resProNames		结果字段
	 * @param propertyNames		参数字段
	 * @param values			参数值
	 * @return
	 */
	public List<Map<String, Object>> findProperties(final Class<T> entityClass, String[] resProNames, String[] propertyNames, Object[] values);
	
	/**
	 * 获取指定属性字段
	 * 
	 * @param resProNames		结果字段
	 * @param propertyNames		参数字段
	 * @param values			参数值
	 * @param offset			记录下标
	 * @param length			
	 * @return
	 */
	public List<Map<String, Object>> findPropertiesForPage(final Class<T> entityClass, String[] resProNames, String[] propertyNames, Object[] values, Integer offset, Integer length);

	/**
	 * 删除
	 * 
	 * @param entity
	 */
	public void delete(T entity);
	
	/**
	 * 批量删除
	 * 
	 * @param entities
	 */
	public void delete(Collection<T> entities);
}
