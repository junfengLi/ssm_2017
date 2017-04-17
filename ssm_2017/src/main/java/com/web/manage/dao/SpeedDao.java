/*
* @ClassName:SpeedDao.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-04-14
*/
package com.web.manage.dao;

import com.web.commons.dao.BaseDao;
import com.web.manage.pojo.Speed;
import java.util.List;

public interface SpeedDao extends BaseDao<Speed> {
	List<Speed> findByUserId(String userid);
	Speed findByInfoId(String infoid);
}