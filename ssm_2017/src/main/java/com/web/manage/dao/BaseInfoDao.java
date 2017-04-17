/*
* @ClassName:BaseInfoDao.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-04-15
*/
package com.web.manage.dao;

import java.util.List;

import com.web.commons.dao.BaseDao;
import com.web.manage.pojo.BaseInfo;

public interface BaseInfoDao extends BaseDao<BaseInfo> {
	List<BaseInfo> findByUserId(String userid);
}