package com.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.Customer;
import com.crm.domain.PageBean;

public interface CustomerService {

	void save(Customer customer) throws Exception;

	PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize)
			throws Exception;

	Customer findById(Long cust_id) throws Exception;

	void delete(Customer customer) throws Exception;

	void update(Customer customer) throws Exception;

	List<Customer> findAll() throws Exception;

}
