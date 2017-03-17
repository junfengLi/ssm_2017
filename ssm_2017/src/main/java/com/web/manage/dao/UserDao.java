/*
* @ClassName:UserDao.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-03-10
*/
package com.web.manage.dao;

import java.util.List;

import com.web.commons.dao.BaseDao;
import com.web.manage.pojo.Permission;
import com.web.manage.pojo.Role;
import com.web.manage.pojo.User;

public interface UserDao extends BaseDao<User> {

//	List<Role> selectRoleByLoginName(String loginName);
//    
//    List<Permission> selectPermissionByRoleCode(String roleCode);
}