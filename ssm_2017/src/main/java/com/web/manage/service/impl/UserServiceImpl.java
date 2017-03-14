package com.web.manage.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.web.manage.dao.UserDao;
import com.web.manage.pojo.User;
import com.web.manage.service.UserService;

@Component("userService")
@Transactional  
public class UserServiceImpl implements UserService {
	@Autowired 
	private UserDao userDao;

	@Override
	public List<User> getUsers() {
		return userDao.selectByStatement(new User());
	}

	@Override
	public User findByLoginName(String loginName) {
		User user = new User();
		user.setLoginname(loginName);
		List<User> users = userDao.selectByStatement(user);
		if (CollectionUtils.isEmpty(users) && users.size() != 0) {
			return users.get(0);
		} else {
			return null;
		}
	}
	

}
