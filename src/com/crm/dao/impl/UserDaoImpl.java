package com.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.crm.dao.UserDao;
import com.crm.domain.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public User login(User user) throws Exception {
		List<User> users = (List<User>) this.getHibernateTemplate()
				.find("from User where user_code=? and user_password=?", user.getUser_code(), user.getUser_password());
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

}
