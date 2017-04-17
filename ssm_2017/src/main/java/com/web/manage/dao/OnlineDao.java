/*
* @ClassName:OnlineDao.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-04-14
*/
package com.web.manage.dao;

import com.web.commons.dao.BaseDao;
import com.web.manage.pojo.Online;
import java.util.List;

public interface OnlineDao extends BaseDao<Online> {
	List<Online> findByUserId(String userid);
	Online findByInfoId(String infoid);
}