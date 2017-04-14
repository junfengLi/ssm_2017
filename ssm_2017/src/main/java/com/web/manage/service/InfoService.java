package com.web.manage.service;

import com.web.commons.jqgrid.UIPage;
import com.web.manage.pojo.Info;


public interface InfoService {
	Info findById(String id);
	void saveInfo(Info info);
	void deleteInfoById(String id);
	UIPage getPage(Info info, int pageNumber, int pageSize);	
}
