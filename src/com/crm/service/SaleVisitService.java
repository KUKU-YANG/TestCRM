package com.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.PageBean;
import com.crm.domain.SaleVisit;

public interface SaleVisitService {
	PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize)
			throws Exception;

	void save(SaleVisit saleVisit) throws Exception;

	SaleVisit findById(String visit_id) throws Exception;

	void update(SaleVisit saleVisit) throws Exception;

	void delete(SaleVisit saleVisit) throws Exception;
}
