package com.crm.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 客户实体
 * 
 * @author ljw
 *
 */
public class Customer {
	private Long cust_id;
	private String cust_name;
	private String cust_phone;
	private String cust_mobile;
	private String cust_image;
	// 客户和字典是多对一，需要在多的一方放置一的一方的对象
	private BaseDict baseDictSource;
	private BaseDict baseDictIndustry;
	private BaseDict baseDictLevel;
	// 客户与联系人是一对多，需要放置联系人集合
	private Set<LinkMan> linkMans = new HashSet<LinkMan>();

	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_phone() {
		return cust_phone;
	}

	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}

	public String getCust_mobile() {
		return cust_mobile;
	}

	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}

	public String getCust_image() {
		return cust_image;
	}

	public void setCust_image(String cust_image) {
		this.cust_image = cust_image;
	}

	public BaseDict getBaseDictSource() {
		return baseDictSource;
	}

	public void setBaseDictSource(BaseDict baseDictSource) {
		this.baseDictSource = baseDictSource;
	}

	public BaseDict getBaseDictIndustry() {
		return baseDictIndustry;
	}

	public void setBaseDictIndustry(BaseDict baseDictIndustry) {
		this.baseDictIndustry = baseDictIndustry;
	}

	public BaseDict getBaseDictLevel() {
		return baseDictLevel;
	}

	public void setBaseDictLevel(BaseDict baseDictLevel) {
		this.baseDictLevel = baseDictLevel;
	}

	public Set<LinkMan> getLinkMans() {
		return linkMans;
	}

	public void setLinkMans(Set<LinkMan> linkMans) {
		this.linkMans = linkMans;
	}
}
