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
	// 获得模型
	private User user = new User();

	@Override
	public User getModel() {
		return user;
	}

	// 注入service
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 用户注册
	 * 
	 * @throws Exception
	 */
	public String regist() throws Exception {
		userService.regist(user);
		this.addActionMessage("注册成功");
		return LOGIN;
	}

	/**
	 * 用户登录
	 * 
	 * @throws Exception
	 */
	public String login() throws Exception {
		User existUser = userService.login(user);
		if (existUser == null) {
			this.addActionError("用户名或密码错误");
			return LOGIN;
		} else if (existUser.getUser_state().equals("0")) {
			this.addActionError("该用户已被停用");
			return LOGIN;
		} else {
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return SUCCESS;
		}
	}

	/**
	 * 跳转到修改密码页面
	 */
	public String updateUI() throws Exception {
		return "updateUI";
	}

	/**
	 * 修改密码
	 */
	public String update() throws Exception {
		userService.update(user);
		this.addActionMessage("修改成功");
		return LOGIN;
	}

	/**
	 * 退出系统
	 */
	public String logout() throws Exception {
		ActionContext.getContext().getSession().clear();
		return "logout";
	}

	/**
	 * 异步查询用户
	 */
	public String findUser() throws Exception {
		List<User> list = userService.findAll();
		// 将list转成json
		JSONArray jsonArray = JSONArray.fromObject(list);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
