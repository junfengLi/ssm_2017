package com.web.manage.service;

import com.web.commons.jqgrid.UIPage;
import com.web.manage.pojo.Times;


public interface TimesService {
	Times findById(String id);
	void saveTimes(Times times);
	void deleteTimesById(String id);
	UIPage getPage(Times times, int pageNumber, int pageSize);	
	
	int reportCount(Times times);
}
