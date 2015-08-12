package com.lin.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;

/**
 * 
 * 
 * @ClassName: IBaseService
 * @Description: 通用service接口
 * @author xuelin
 * @date Jun 29, 2015 2:50:30 PM
 * 
 * @param <T>
 * @param <PK>
 */
public interface BaseService<T, PK extends Serializable> {

	/**
	 * 通过id获取记录
	 * 
	 * @param id
	 * @return
	 */
	public T find(PK id);
	
	/**
	 * 分页查询
	 * 
	 * @param offset
	 * @param length
	 * @return
	 */
	public List<T> findForPage(Integer offset, Integer length);

	/**
	 * 给行数据上锁 在一个事务内有效
	 * 
	 * @param id
	 * @return
	 */
	public T findForUpdate(PK id);

	/**
	 * 给数据上锁 在一个事务内有效
	 * 
	 * @param id
	 * @param lockMode
	 * @return
	 */
	public T findForUpdate(PK id, LockMode lockMode);

	/**
	 * 通过id获取记录
	 * 
	 * @param id
	 * @return
	 */
	public T load(PK id);

	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 */
	public void save(T entity);

	/**
	 * 更新或保存
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(T entity);

	/**
	 * 通过属性名查询
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<T> findByProperty(String propertyName, Object value);

	/**
	 * 通过多个属性名查询
	 * 
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	public List<T> findByProperties(String[] propertyNames, Object[] values);

	/**
	 * 通过属性名分页查询
	 * 
	 * @param propertyName
	 * @param value
	 * @param offset
	 * @param length
	 * @return
	 */
	public List<T> findByPropertyForPage(String propertyName, Object value, Integer offset, Integer length);

	/**
	 * 通过多个属性名分页查询
	 * 
	 * @param propertyNames
	 * @param values
	 * @param offset
	 * @param length
	 * @return
	 */
	public List<T> findByPropertiesForPage(String[] propertyNames, Object[] values, Integer offset, Integer length);

	/**
	 * 统计记录总数
	 * 
	 * @param entityClass
	 * @return
	 */
	public long count();

	/**
	 * 通过某个属性统计记录总数
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public long countByProperty(String propertyName, Object value);

	/**
	 * 通过某个多个属性统计记录总数
	 * 
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	public long countByProperties(String[] propertyNames, Object[] values);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * 批量更新
	 * 
	 * @param entities
	 */
	public void update(Collection<T> entities);

	/**
	 * 通过id获取指定属性
	 * 
	 * @param resProNames
	 *            结果字段
	 * @param id
	 * @return
	 */
	public Map<String, Object> findProperties(String[] resProNames, PK id);

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
	public List<Map<String, Object>> findProperties(String[] resProNames, String[] propertyNames, Object[] values);

	/**
	 * 获取指定属性字段
	 * 
	 * @param resProNames
	 *            结果字段
	 * @param propertyName
	 *            参数字段
	 * @param value
	 *            参数值
	 * @return
	 */
	public List<Map<String, Object>> findProperties(String[] resProNames, String propertyName, Object value);

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
	public List<Map<String, Object>> findPropertiesForPage(String[] resProNames, String[] propertyNames, Object[] values, Integer offset, Integer length);

	/**
	 * 获取指定属性字段
	 * 
	 * @param resProNames
	 *            结果字段
	 * @param propertyName
	 *            参数字段
	 * @param value
	 *            参数值
	 * @param offset
	 *            记录下标
	 * @param length
	 * @return
	 */
	public List<Map<String, Object>> findPropertiesForPage(String[] resProNames, String propertyName, Object value, Integer offset, Integer length);
	
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
