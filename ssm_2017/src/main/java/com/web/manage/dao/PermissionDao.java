/*
* @ClassName:PermissionDao.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-03-10
*/
package com.web.manage.dao;

import com.web.manage.pojo.Permission;
import java.util.List;

public interface PermissionDao {
    int deleteByPrimaryKey(String id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String id);

    List<Permission> selectByStatement(Permission record);

    int selectByStatementCount(Permission record);

    int updateByPrimaryKeySelective(Permission record);
}