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
	 * ��ҳ��ѯ��ϵ��
	 * 
	 * @throws Exception
	 */
	public String findAll() throws Exception {
		// ͬ����ѯ�ͻ��б���Ϊ���水�����ͻ���ѯ��׼��
		List<Customer> customerList = customerService.findAll();
		ActionContext.getContext().getValueStack().set("customerList", customerList);

		// ʹ��DetachedCriteria��������ѯ
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		// ��������
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
	 * ��ת��������ϵ��ҳ��
	 * 
	 * @throws Exception
	 */
	public String saveUI() throws Exception {
		// ��ѯ���пͻ�
		List<Customer> list = customerService.findAll();
		// ��list����ֵջ
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}

	/**
	 * ������ϵ��
	 * 
	 * @throws Exception
	 */
	public String save() throws Exception {
		linkManService.save(linkMan);
		return "saveSuccess";
	}

	/**
	 * ��ת���޸���ϵ��ҳ��
	 * 
	 * @throws Exception
	 */
	public String updateUI() throws Exception {
		// ��ѯ���пͻ�
		List<Customer> lsit = customerService.findAll();
		// ��ѯ����ϵ��
		linkMan = linkManService.findById(linkMan.getLkm_id());
		// ���ͻ����Ϸ���ֵջ
		ActionContext.getContext().getValueStack().set("list", lsit);
		// ����ϵ�˶������ֵջ
		ActionContext.getContext().getValueStack().push(linkMan);
		return "updateUI";
	}

	/**
	 * �޸���ϵ��
	 * 
	 * @throws Exception
	 */
	public String update() throws Exception {
		linkManService.update(linkMan);
		return "updateSuccess";
	}

	/**
	 * ɾ����ϵ��
	 */
	public String delete() throws Exception {
		// �Ȳ�ѯ�������޷�����ɾ��
		linkMan = linkManService.findById(linkMan.getLkm_id());
		// ��ɾ��
		linkManService.delete(linkMan);
		return "deleteSuccess";
	}
}