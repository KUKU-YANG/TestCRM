package com.crm.web.action;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.crm.domain.Customer;
import com.crm.domain.PageBean;
import com.crm.domain.SaleVisit;
import com.crm.domain.User;
import com.crm.service.CustomerService;
import com.crm.service.SaleVisitService;
import com.crm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
	private SaleVisit saleVisit = new SaleVisit();

	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}

	private SaleVisitService saleVisitService;

	public void setSaleVisitService(SaleVisitService saleVisitService) {
		this.saleVisitService = saleVisitService;
	}

	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private Integer currPage = 1;

	public void setCurrPage(Integer currPage) {
		if (currPage == null) {
			currPage = 1;
		} else
			this.currPage = currPage;
	}

	private Integer pageSize = 3;

	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 3;
		} else
			this.pageSize = pageSize;
	}

	private Date visit_begin_time;

	public void setVisit_begin_time(Date visit_begin_time) {
		this.visit_begin_time = visit_begin_time;
	}

	public Date getVisit_begin_time() {
		return visit_begin_time;
	}

	private Date visit_end_time;

	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}

	public Date getVisit_end_time() {
		return visit_end_time;
	}

	/**
	 * 查询客户拜访记录
	 */
	public String findAll() throws Exception {
		// 使用DetachedCriteria对象来查询
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		// 设置条件
		if (visit_begin_time != null) {
			detachedCriteria.add(Restrictions.ge("visit_time", visit_begin_time));
		}
		if (visit_end_time != null) {
			detachedCriteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}

	/**
	 * 跳转到添加客户拜访记录页面
	 */
	public String saveUI() {
		return "saveUI";
	}

	/**
	 * 添加客户拜访记录
	 */
	public String save() throws Exception {
		saleVisitService.save(saleVisit);
		return "saveSuccess";
	}

	/**
	 * 跳转到修改客户拜访记录页面
	 */
	public String updateUI() throws Exception {
		// 查询所有客户和用户
		List<Customer> customerList = customerService.findAll();
		List<User> userList = userService.findAll();
		// 查询该客户拜访记录
		saleVisit = saleVisitService.findById(saleVisit.getVisit_id());
		// 将客户和用户集合放入值栈
		ActionContext.getContext().getValueStack().set("customerList", customerList);
		ActionContext.getContext().getValueStack().set("userList", userList);
		// 将客户拜访记录放入值栈
		ActionContext.getContext().getValueStack().push(saleVisit);
		return "updateUI";
	}

	/**
	 * 修改客户拜访记录
	 */
	public String update() throws Exception {
		saleVisitService.update(saleVisit);
		return "updateSuccess";
	}

	/**
	 * 删除客户拜访记录
	 */
	public String delete() throws Exception {
		saleVisit = saleVisitService.findById(saleVisit.getVisit_id());
		saleVisitService.delete(saleVisit);
		return "deleteSuccess";
	}
}
