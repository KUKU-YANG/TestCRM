package com.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.LinkMan;
import com.crm.domain.PageBean;

public interface LinkManService {
	PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize)
			throws Exception;

	void save(LinkMan linkMan) throws Exception;

	LinkMan findById(Long lkm_id) throws Exception;

	void update(LinkMan linkMan) throws Exception;

	void delete(LinkMan linkMan) throws Exception;
}
