package com.crm.web.action;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.crm.domain.Customer;
import com.crm.domain.PageBean;
import com.crm.service.CustomerService;
import com.crm.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	private Customer customer = new Customer();

	@Override
	public Customer getModel() {
		return customer;
	}

	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// ʹ��set�����ķ�ʽ��������
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

	// �ļ��ϴ��ṩ��3������
	private String uploadFileName; // �ļ�����
	private File upload; // �ϴ����ļ�
	private String uploadContentType; // �ļ�����

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * ��ת�����ӿͻ�ҳ��
	 */
	public String saveUI() {
		return "saveUI";
	}

	/**
	 * ���ӿͻ�
	 * 
	 * @throws Exception
	 */
	public String save() throws Exception {
		if (upload != null) {
			// �����ϴ�·��
			String path = "D:/stsworkspace/crm01/WebContent/upload";
			// һ��Ŀ¼�²�Ҫ��ŵ���ͬ�ļ���������ļ�����
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			// һ��Ŀ¼�²�Ҫ��Ź����ļ���Ŀ¼���룩
			String realPath = UploadUtils.getPath(uuidFileName);
			// ����Ŀ¼
			String url = path + realPath;
			File file = new File(url);
			if (!file.exists()) {
				file.mkdirs();
			}
			// �ϴ�
			File dictFile = new File(url + "/" + uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			// �ֶ�����image������ֵ
			customer.setCust_image(url + "/" + uuidFileName);
		}

		customerService.save(customer);
		return "saveSuccess";
	}

	/**
	 * ��ҳ��ѯ�ͻ��б�
	 * 
	 * @throws Exception
	 */
	public String findAll() throws Exception {
		// ʹ��DetachedCriteria��������ѯ
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		// ��������
		if (customer.getCust_name() != null) {
			detachedCriteria.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
		}
		if (customer.getBaseDictSource() != null) {
			if (customer.getBaseDictSource().getDict_id() != null
					&& !"".equals(customer.getBaseDictSource().getDict_id())) {
				detachedCriteria
						.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
			}
		}
		if (customer.getBaseDictLevel() != null) {
			if (customer.getBaseDictLevel().getDict_id() != null
					&& !"".equals(customer.getBaseDictLevel().getDict_id())) {
				detachedCriteria
						.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
			}
		}
		if (customer.getBaseDictIndustry() != null) {
			if (customer.getBaseDictIndustry().getDict_id() != null
					&& !"".equals(customer.getBaseDictIndustry().getDict_id())) {
				detachedCriteria
						.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
			}
		}
		PageBean<Customer> pageBea = customerService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBea);
		return "findAll";
	}

	/**
	 * ɾ���ͻ�
	 * 
	 * @throws Exception
	 */
	public String delete() throws Exception {
		// �Ȳ�ѯ��ɾ��,�����޷�����ɾ��
		customer = customerService.findById(customer.getCust_id());
		// ɾ��ͼƬ
		if (customer.getCust_image() != null) {
			File file = new File(customer.getCust_image());
			if (file.exists()) {
				file.delete();
			}
		}
		// ɾ���ͻ�
		customerService.delete(customer);
		return "deleteSuccess";
	}

	/**
	 * ��ת���޸Ŀͻ�ҳ��
	 * 
	 * @throws Exception
	 */
	public String updateUI() throws Exception {
		// ����id��ѯ����תҳ���һ�������
		customer = customerService.findById(customer.getCust_id());
		return "updateUI";
	}

	/**
	 * �޸Ŀͻ�
	 */
	public String update() throws Exception {
		// �ж��ļ����Ƿ�ѡ��û�о���ԭ�еģ�����ɾ��ԭ�е��ϴ��µ�
		if (upload != null) {
			// ɾ��ԭ�е�
			String cust_image = customer.getCust_image();
			if (cust_image != null || !"".equals(cust_image)) {
				File file = new File(cust_image);
				file.delete();
			}
			// �ϴ��µ�
			// �����ϴ�·��
			String path = "D:/stsworkspace/crm01/WebContent/upload";
			// һ��Ŀ¼�²�Ҫ��ŵ���ͬ�ļ���������ļ�����
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			// һ��Ŀ¼�²�Ҫ��Ź����ļ���Ŀ¼���룩
			String realPath = UploadUtils.getPath(uuidFileName);
			// ����Ŀ¼
			String url = path + realPath;
			File file = new File(url);
			if (!file.exists()) {
				file.mkdirs();
			}
			// �ϴ�
			File dictFile = new File(url + "/" + uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			// �ֶ�����image������ֵ
			customer.setCust_image(url + "/" + uuidFileName);
		}
		customerService.update(customer);
		return "updateSuccess";
	}

	/**
	 * �첽��ѯ�ͻ�
	 */
	public String findCustomer() throws Exception {
		List<Customer> list = customerService.findAll();
		// ȥ������Ҫתjson������
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "linkMans", "baseDictSource", "baseDictIndustry", "baseDictLevel" });
		// ��listת��json
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charser=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;

	}
}