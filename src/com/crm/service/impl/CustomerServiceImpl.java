package com.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.crm.dao.CustomerDao;
import com.crm.domain.Customer;
import com.crm.domain.PageBean;
import com.crm.service.CustomerService;

@Transactional
public class CustomerServiceImpl implements CustomerService {
	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void save(Customer customer) throws Exception {
		customerDao.save(customer);
	}

	@Override
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize)
			throws Exception {
		PageBean<Customer> pageBean = new PageBean<Customer>();
		// 封装当前页数
		pageBean.setCurrPage(currPage);
		// 封装每页显示记录数
		pageBean.setPageSize(pageSize);
		// 封装总记录数
		Integer totalCount = customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 封装总页数
		Double tc = totalCount.doubleValue();
		Double totalPage = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(totalPage.intValue());
		// 封装每页显示数据的集合
		// 从第几个开始
		Integer begin = (currPage - 1) * pageSize;
		List<Customer> list = customerDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public Customer findById(Long cust_id) throws Exception {
		Customer customer = customerDao.findById(cust_id);
		return customer;
	}

	@Override
	public void delete(Customer customer) throws Exception {
		customerDao.delete(customer);
	}

	@Override
	public void update(Customer customer) throws Exception {
		customerDao.update(customer);
	}

	@Override
	public List<Customer> findAll() throws Exception {
		List<Customer> list = customerDao.findAll();
		return list;
	}

}
