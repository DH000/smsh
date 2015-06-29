package com.lin.service;

import java.io.Serializable;

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
}
