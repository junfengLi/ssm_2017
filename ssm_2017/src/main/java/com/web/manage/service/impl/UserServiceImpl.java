package com.web.manage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.CopyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.web.commons.jqgrid.UIPage;
import com.web.commons.util.BeanCopyUtil;
import com.web.commons.util.IsOrEnum;
import com.web.manage.dao.UserDao;
import com.web.manage.pojo.Role;
import com.web.manage.pojo.User;
import com.web.manage.service.UserService;

@Component("userService")
@Transactional  
public class UserServiceImpl implements UserService {
	@Autowired 
	private UserDao userDao;

	@Override
	public User findById(String id) {
		return userDao.selectByPrimaryKey(id);
	}

	@Override
	public void saveUser(User user) {
		if (StringUtils.isBlank(user.getIsdelete())) {
			user.setIsdelete(IsOrEnum.FOU.getKey());
		}
		if (StringUtils.isBlank(user.getId())) {
			user.setCreatetime(System.currentTimeMillis());
			user.setUpdatetime(System.currentTimeMillis());
			userDao.insert(user);
		} else {
			user.setUpdatetime(System.currentTimeMillis());
			userDao.updateByPrimaryKeySelective(user);
		}
	}

	@Override
	public void deleteUserById(String id) {
		userDao.deleteByPrimaryKey(id);
	}
	

	@Override
	public User findByLoginName(String loginName) {
		User user = new User();
		user.setLoginname(loginName);
		List<User> users = userDao.selectByStatement(user);
		if (CollectionUtils.isNotEmpty(users) && users.size() != 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@Override
	public UIPage getPage(User user, int pageNumber, int pageSize) {
		UIPage page = new UIPage();
		List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
		List<User> users = userDao.selectByStatement(user);
		long count = userDao.selectByStatementCount(user);
		for (User user2 : users) {
			@SuppressWarnings("unchecked")
			Map<String,Object> row=BeanCopyUtil.CopyBeanToMap(user2);
    		rows.add(row);
		}
		page.setRows(rows);
    	page.setRecords(count);
    	return page;
	}

	@Override
	public List<Role> getRolesByLoginName(String loginName) {
		User user = findByLoginName(loginName);
		if (user != null) {
			return user.getRoles();
		} else {
			return null;
		}
	}


	

}
