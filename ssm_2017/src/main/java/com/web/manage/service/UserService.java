package com.web.manage.service;

import java.util.List;
import java.util.Map;

import com.web.commons.jqgrid.UIPage;
import com.web.manage.pojo.Role;
import com.web.manage.pojo.User;


public interface UserService {
	public List<User> getUsers();
	public User findByLoginName(String loginName);
	UIPage getPage(Map<String, Object> searchParams, int pageNumber, int pageSize);
	List<Role> getRolesByLoginName(String loginName);
	
}
