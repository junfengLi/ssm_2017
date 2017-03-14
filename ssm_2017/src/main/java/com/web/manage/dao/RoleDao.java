/*
* @ClassName:RoleDao.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-03-10
*/
package com.web.manage.dao;

import com.web.manage.pojo.Role;
import java.util.List;

public interface RoleDao {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    List<Role> selectByStatement(Role record);

    int selectByStatementCount(Role record);

    int updateByPrimaryKeySelective(Role record);
}