package com.web.manage.service;

import java.util.List;

import com.web.commons.jqgrid.UIPage;
import com.web.manage.pojo.BaseInfo;
import com.web.manage.pojo.BaseInfoResult;
import com.web.manage.pojo.Online;
import com.web.manage.pojo.Speed;


public interface InfoService {
	BaseInfo findById(String id);
	void saveInfo(BaseInfo info);
	void deleteInfoById(String id);
	UIPage getPage(BaseInfo info, int pageNumber, int pageSize);	
	UIPage getPage(BaseInfoResult info, int pageNumber, int pageSize);
	List<BaseInfo> findList(BaseInfo info);
	List<BaseInfo> findList(BaseInfoResult info);
	Speed findSpeedByInfoId(String infoid);
	Online findOnlineByInfoId(String infoid);
	
	void saveSpeed(Speed speed);
	void saveOnline(Online online);
	void deleteSpeedById(String id);
	void deleteOnlineById(String id);
	
	
	
	int reportCount(BaseInfoResult info);
	String reportName(BaseInfoResult info);
}
