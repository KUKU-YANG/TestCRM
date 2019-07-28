package com.crm.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * Õ®”√dao
 */
public interface BaseDao<T> {
	public void save(T t) throws Exception;

	public void update(T t) throws Exception;

	public void delete(T t) throws Exception;

	public T findById(Serializable id) throws Exception;

	public List<T> findAll() throws Exception;

	public Integer findCount(DetachedCriteria detachedCriteria) throws Exception;

	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) throws Exception;
}
