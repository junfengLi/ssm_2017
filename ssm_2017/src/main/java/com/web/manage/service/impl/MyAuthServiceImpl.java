package com.web.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.web.manage.dao.UserDao;
import com.web.manage.pojo.User;
import com.web.manage.service.MyAuthService;

public class MyAuthServiceImpl implements MyAuthService {

	@Autowired 
	private UserDao userDao;

	@Override
	public User getUserByUserName(String loginName) {  
		User user = new User();
		user.setLoginname(loginName);
		List<User> users = userDao.selectByStatement(user);
		if (CollectionUtils.isNotEmpty(users)) {
			return users.get(0);
		} else {
			return null;
		} 
	}  
	@Override
    public List<String> getPermissionsByUserName(String loginName) {  
        List<String> roles = getRolesByUserName(loginName);
        List<String> permissionList = new ArrayList<>();
        for (String roleCode : roles) {  
            List<String> permissions = userDao.selectPermissionCodeByRoleCode(roleCode);
            permissionList.addAll(permissions);
        }  
        return permissionList;  
    }
	@Override
	public List<String> getRolesByUserName(String loginName) {
		return userDao.selectRoleCodeByLoginName(loginName);
	}  

}
