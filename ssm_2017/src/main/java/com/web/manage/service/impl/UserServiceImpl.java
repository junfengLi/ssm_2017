package com.web.manage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.web.commons.dao.BaseDao;
import com.web.commons.jqgrid.UIPage;
import com.web.manage.dao.UserDao;
import com.web.manage.pojo.Role;
import com.web.manage.pojo.User;
import com.web.manage.service.UserService;

@Component("userService")
@Transactional  
public class UserServiceImpl implements UserService {
	@Autowired 
	private UserDao userDao;
//	@Autowired
//	private BaseDao baseDao;

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

	@Override
	public UIPage getPage(Map<String, Object> searchParams, int pageNumber, int pageSize) {
		
//		List<Map<String, Object>> maps = baseDao.select(new SqlVO("select * from li_sys_user"));
//		System.out.println(maps);
//		userDao.selectByStatementCount(record)
		UIPage page = new UIPage();
		List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
		for (int i = Integer.valueOf(pageNumber) - 1 ; i < Integer.valueOf(pageNumber) *pageSize ; i++) {
    		Map<String,Object> row=new HashMap<String, Object>();
    		row.put("id", i);
    		row.put("name", "name" + i);
    		rows.add(row);
		}
		page.setRows(rows);
    	page.setRecords(20);
    	return page;
	}

	@Override
	public List<Role> getRolesByLoginName(String loginName) {
		return null;// userDao.selectRoleByLoginName(loginName);
	}
	

}
