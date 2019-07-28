package com.crm.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.crm.dao.UserDao;
import com.crm.domain.User;
import com.crm.service.UserService;
import com.crm.utils.MD5Utils;
import com.opensymphony.xwork2.ActionContext;

@Transactional
public class UserServiceImpl implements UserService {
	// 注入dao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 用户注册
	 * 
	 * @throws Exception
	 */
	@Override
	public void regist(User user) throws Exception {
		// 将密码加密
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");
		userDao.save(user);
	}

	@Override
	public User login(User user) throws Exception {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		User existUser = userDao.login(user);
		return existUser;
	}

	@Override
	public void update(User user) throws Exception {
		User existUser = (User) ActionContext.getContext().getSession().get("existUser");
		existUser.setUser_password(MD5Utils.md5(user.getUser_password()));
		userDao.update(existUser);
	}

	@Override
	public List<User> findAll() throws Exception {
		List<User> list = userDao.findAll();
		return list;
	}

}
