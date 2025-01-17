package com.crm.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.crm.domain.Customer;
import com.crm.domain.LinkMan;
import com.crm.domain.PageBean;
import com.crm.service.CustomerService;
import com.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	private LinkMan linkMan = new LinkMan();

	@Override
	public LinkMan getModel() {
		return linkMan;
	}

	private LinkManService linkManService;

	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}

	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
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

	/**
	 * 分页查询联系人
	 * 
	 * @throws Exception
	 */
	public String findAll() throws Exception {
		// 同步查询客户列表，为下面按所属客户查询做准备
		List<Customer> customerList = customerService.findAll();
		ActionContext.getContext().getValueStack().set("customerList", customerList);

		// 使用DetachedCriteria对象来查询
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		// 设置条件
		if (linkMan.getLkm_name() != null && !"".equals(linkMan.getLkm_name())) {
			detachedCriteria.add(Restrictions.like("lkm_name", "%" + linkMan.getLkm_name() + "%"));
		}
		if (linkMan.getLkm_gender() != null && !"".equals(linkMan.getLkm_gender())) {
			detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		if (linkMan.getCustomer() != null) {
			if (linkMan.getCustomer().getCust_id() != null && !"".equals(linkMan.getCustomer().getCust_id())) {
				detachedCriteria.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
			}
		}

		PageBean<LinkMan> pageBea = linkManService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBea);
		return "findAll";
	}

	/**
	 * 跳转到添加联系人页面
	 * 
	 * @throws Exception
	 */
	public String saveUI() throws Exception {
		// 查询所有客户
		List<Customer> list = customerService.findAll();
		// 将list放入值栈
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}

	/**
	 * 添加联系人
	 * 
	 * @throws Exception
	 */
	public String save() throws Exception {
		linkManService.save(linkMan);
		return "saveSuccess";
	}

	/**
	 * 跳转到修改联系人页面
	 * 
	 * @throws Exception
	 */
	public String updateUI() throws Exception {
		// 查询所有客户
		List<Customer> lsit = customerService.findAll();
		// 查询该联系人
		linkMan = linkManService.findById(linkMan.getLkm_id());
		// 将客户集合放入值栈
		ActionContext.getContext().getValueStack().set("list", lsit);
		// 将联系人对象放入值栈
		ActionContext.getContext().getValueStack().push(linkMan);
		return "updateUI";
	}

	/**
	 * 修改联系人
	 * 
	 * @throws Exception
	 */
	public String update() throws Exception {
		linkManService.update(linkMan);
		return "updateSuccess";
	}

	/**
	 * 删除联系人
	 */
	public String delete() throws Exception {
		// 先查询，否则无法级联删除
		linkMan = linkManService.findById(linkMan.getLkm_id());
		// 再删除
		linkManService.delete(linkMan);
		return "deleteSuccess";
	}
}
