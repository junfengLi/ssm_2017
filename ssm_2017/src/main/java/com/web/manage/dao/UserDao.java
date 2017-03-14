/*
* @ClassName:UserDao.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-03-10
*/
package com.web.manage.dao;

import com.web.manage.pojo.User;
import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    List<User> selectByStatement(User record);

    int selectByStatementCount(User record);

    int updateByPrimaryKeySelective(User record);
    
    List<String> selectRoleCodeByLoginName(String loginName);
    
    List<String> selectPermissionCodeByRoleCode(String roleCode);
}