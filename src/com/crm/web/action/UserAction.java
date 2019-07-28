package com.crm.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.crm.domain.User;
import com.crm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	// ���ģ��
	private User user = new User();

	@Override
	public User getModel() {
		return user;
	}

	// ע��service
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * �û�ע��
	 * 
	 * @throws Exception
	 */
	public String regist() throws Exception {
		userService.regist(user);
		this.addActionMessage("ע��ɹ�");
		return LOGIN;
	}

	/**
	 * �û���¼
	 * 
	 * @throws Exception
	 */
	public String login() throws Exception {
		User existUser = userService.login(user);
		if (existUser == null) {
			this.addActionError("�û������������");
			return LOGIN;
		} else if (existUser.getUser_state().equals("0")) {
			this.addActionError("���û��ѱ�ͣ��");
			return LOGIN;
		} else {
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return SUCCESS;
		}
	}

	/**
	 * ��ת���޸�����ҳ��
	 */
	public String updateUI() throws Exception {
		return "updateUI";
	}

	/**
	 * �޸�����
	 */
	public String update() throws Exception {
		userService.update(user);
		this.addActionMessage("�޸ĳɹ�");
		return LOGIN;
	}

	/**
	 * �˳�ϵͳ
	 */
	public String logout() throws Exception {
		ActionContext.getContext().getSession().clear();
		return "logout";
	}

	/**
	 * �첽��ѯ�û�
	 */
	public String findUser() throws Exception {
		List<User> list = userService.findAll();
		// ��listת��json
		JSONArray jsonArray = JSONArray.fromObject(list);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
