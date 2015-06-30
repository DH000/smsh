package com.lin.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import com.lin.dao.IBaseDao;
import com.lin.service.IBaseService;
import com.lin.utils.GenericsUtils;

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
	@SuppressWarnings("unchecked")
	public Class<T> getEntityClass(){
		if(null == entityClass){
			entityClass = GenericsUtils.getSuperClassGenricType(getClass());
		}
		
		return entityClass;
	}

	/**
	 * 通过id获取记录
	 * 
	 * @param id 表唯一标识
	 * @return
	 */
	@Override
	public T find(PK id) {
		if(null == id){
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
		if(null == id){
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
		if(null == entity){
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
		if(null == entity){
			throw new IllegalArgumentException("entity参数不能为null");
		}
		
		baseDao.saveOrUpdate(entity);
	}

}
