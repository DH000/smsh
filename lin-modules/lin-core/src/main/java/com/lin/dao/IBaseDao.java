package com.lin.dao;

import java.io.Serializable;

public interface IBaseDao<T, PK extends Serializable> {
	/**
	 * 通过id获取记录
	 * 
	 * @param id
	 * @return
	 */
	public T find(final Class<T> entityClass, PK id);
	
	/**
	 * 通过id获取记录
	 * 
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
}
