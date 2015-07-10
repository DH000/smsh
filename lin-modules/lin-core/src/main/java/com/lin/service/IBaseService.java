package com.lin.service;

import java.io.Serializable;
import java.util.List;

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
public interface IBaseService<T, PK extends Serializable> {

	/**
	 * 通过id获取记录
	 * 
	 * @param id
	 * @return
	 */
	public T find(PK id);
	
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
	public List<T> findByPropertyForPage(String propertyName, Object value, int offset, int length);
	
	/**
	 * 通过多个属性名分页查询
	 * 
	 * @param propertyNames
	 * @param values
	 * @param offset
	 * @param length
	 * @return
	 */
	public List<T> findByPropertiesForPage(String[] propertyNames, Object[] values, int offset, int length);
	
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
}
