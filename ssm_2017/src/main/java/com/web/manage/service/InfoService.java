package com.web.manage.service;

import com.web.commons.jqgrid.UIPage;
import com.web.manage.pojo.BaseInfo;
import com.web.manage.pojo.Online;
import com.web.manage.pojo.Speed;


public interface InfoService {
	BaseInfo findById(String id);
	void saveInfo(BaseInfo info);
	void deleteInfoById(String id);
	UIPage getPage(BaseInfo info, int pageNumber, int pageSize);	
	
	Speed findSpeedByInfoId(String infoid);
	Online findOnlineByInfoId(String infoid);
	
	void saveSpeed(Speed speed);
	void saveOnline(Online online);
}
