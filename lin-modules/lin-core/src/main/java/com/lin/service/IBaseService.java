package com.lin.service;

import java.io.Serializable;

/**
 * 通用service
 * 
 * @author xuelin
 *
 * 广州房友圈网络技术有限公司
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
