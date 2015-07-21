package com.lin.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.LockMode;
import org.springframework.stereotype.Service;

import com.lin.dao.IBaseDao;
import com.lin.service.IBaseService;
import com.lin.utils.Collections3;
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
@Service
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
	 * 给行数据上锁 在一个事务内有效
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public T findForUpdate(PK id) {
		if (null == id) {
			throw new IllegalArgumentException("id不能为null");
		}

		return findForUpdate(id, LockMode.UPGRADE_NOWAIT);
	}

	/**
	 * 给数据上锁 在一个事务内有效
	 * 
	 * @param id
	 * @param lockMode
	 * @return
	 */
	@Override
	public T findForUpdate(PK id, LockMode lockMode) {
		if (null == id) {
			throw new IllegalArgumentException("id不能为null");
		}

		return baseDao.findForUpdate(getEntityClass(), id, lockMode);
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
	public void save(T entity) {
		if (null == entity) {
			throw new IllegalArgumentException("entity参数不能为null");
		}

		baseDao.save(entity);
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
	 * @param propertyName
	 * @param value
	 * @return
	 */
	@Override
	public List<T> findByProperty(String propertyName, Object value) {
		if (null == propertyName) {
			throw new IllegalArgumentException("属性参数不能为null");
		}
		
		return baseDao.findByProperty(getEntityClass(), propertyName, value);
	}

	/**
	 * 通过多个属性名查询
	 * 
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	@Override
	public List<T> findByProperties(String[] propertyNames, Object[] values) {
		if (null == propertyNames || 0 == propertyNames.length) {
			throw new IllegalArgumentException("属性参数不能为空");
		}
		return baseDao.findByProperties(getEntityClass(), propertyNames, values);
	}

	/**
	 * 通过属性名分页查询
	 * 
	 * @param propertyName
	 * @param value
	 * @param offset
	 * @param length
	 * @return
	 */
	@Override
	public List<T> findByPropertyForPage(String propertyName, Object value, Integer offset, Integer length) {
		if (null == propertyName) {
			throw new IllegalArgumentException("属性参数不能为null");
		}
		return baseDao.findByPropertyForPage(getEntityClass(), propertyName, value, offset, length);
	}

	/**
	 * 通过多个属性名分页查询
	 * 
	 * @param propertyNames
	 * @param values
	 * @param offset
	 * @param length
	 * @return
	 */
	@Override
	public List<T> findByPropertiesForPage(String[] propertyNames, Object[] values, Integer offset, Integer length) {
		if (null == propertyNames || 0 == propertyNames.length) {
			throw new IllegalArgumentException("属性参数不能为空");
		}
		return baseDao.findByPropertiesForPage(getEntityClass(), propertyNames, values, offset, length);
	}

	/**
	 * 统计记录总数
	 * 
	 * @return
	 */
	@Override
	public long count() {
		return baseDao.count(getEntityClass());
	}

	/**
	 * 通过某个属性统计记录总数
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	@Override
	public long countByProperty(String propertyName, Object value) {
		if (null == propertyName) {
			throw new IllegalArgumentException("属性参数不能为null");
		}
		return baseDao.countByProperty(getEntityClass(), propertyName, value);
	}

	/**
	 * 通过某个多个属性统计记录总数
	 * 
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	@Override
	public long countByProperties(String[] propertyNames, Object[] values) {
		if (null == propertyNames || 0 == propertyNames.length) {
			throw new IllegalArgumentException("属性参数不能为空");
		}
		return baseDao.countByProperties(getEntityClass(), propertyNames, values);
	}

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	@Override
	public void update(T entity) {
		if (null == entity) {
			throw new IllegalArgumentException("entity参数不能为null");
		}
		
		baseDao.update(getEntityClass(), entity);
	}

	/**
	 * 批量更新
	 * 
	 * @param entities
	 */
	@Override
	public void update(Collection<T> entities) {
		if(Collections3.isEmpty(entities)){
			throw new IllegalArgumentException("entities参数不能为空");
		}
		baseDao.update(getEntityClass(), entities);
	}

	/**
	 * 通过id获取指定属性
	 * 
	 * @param resProNames
	 *            结果字段
	 * @param id
	 * @return
	 */
	@Override
	public Map<String, Object> findProperties(String[] resProNames, PK id) {
		if (null == resProNames || 0 == resProNames.length) {
			throw new IllegalArgumentException("结果属性参数不能为空");
		}
		if(null == id){
			throw new IllegalArgumentException("id参数不能为null");
		}
		return baseDao.findProperties(getEntityClass(), resProNames, id);
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
	@Override
	public List<Map<String, Object>> findProperties(String[] resProNames, String[] propertyNames, Object[] values) {
		if (null == resProNames || 0 == resProNames.length) {
			throw new IllegalArgumentException("结果属性字段不能为空");
		}
		if (null == propertyNames || 0 == propertyNames.length) {
			throw new IllegalArgumentException("属性参数不能为空");
		}
		return baseDao.findProperties(getEntityClass(), resProNames, propertyNames, values);
	}

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
	@Override
	public List<Map<String, Object>> findProperties(String[] resProNames, String propertyName, Object value) {
		if (null == resProNames || 0 == resProNames.length) {
			throw new IllegalArgumentException("结果属性字段不能为空");
		}
		if (null == propertyName) {
			throw new IllegalArgumentException("属性参数不能为空");
		}
		return findProperties(resProNames, new String[] { propertyName }, new Object[] { value });
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
	@Override
	public List<Map<String, Object>> findPropertiesForPage(String[] resProNames, String[] propertyNames, Object[] values, Integer offset, Integer length) {
		if (null == resProNames || 0 == resProNames.length) {
			throw new IllegalArgumentException("结果属性字段不能为空");
		}
		if (null == propertyNames || 0 == propertyNames.length) {
			throw new IllegalArgumentException("属性参数不能为空");
		}
		return baseDao.findPropertiesForPage(getEntityClass(), resProNames, propertyNames, values, offset, length);
	}

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
	@Override
	public List<Map<String, Object>> findPropertiesForPage(String[] resProNames, String propertyName, Object value, Integer offset, Integer length) {
		if (null == resProNames || 0 == resProNames.length) {
			throw new IllegalArgumentException("结果属性字段不能为空");
		}
		if (null == propertyName) {
			throw new IllegalArgumentException("属性参数不能为空");
		}
		return findPropertiesForPage(resProNames, new String[] { propertyName }, new Object[] { value }, offset, length);
	}
	
	/**
	 * 删除
	 * 
	 * @param entity
	 */
	@Override
	public void delete(T entity){
		baseDao.delete(entity);
	}
	
	/**
	 * 批量删除
	 * 
	 * @param entities
	 */
	@Override
	public void delete(Collection<T> entities){
		baseDao.delete(entities);
	}
}
