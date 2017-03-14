package com.web.manage.service;

import java.util.List;

import com.web.manage.pojo.User;


public interface UserService {
	public List<User> getUsers();
	public User findByLoginName(String loginName);
	
}
