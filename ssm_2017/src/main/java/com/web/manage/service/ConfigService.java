package com.web.manage.service;

import com.web.commons.jqgrid.UIPage;
import com.web.manage.pojo.Config;


public interface ConfigService {
	Config findById(String id);
	void saveConfig(Config config);
	void deleteConfigById(String id);
	UIPage getPage(Config config, int pageNumber, int pageSize);
	Config findByUserId(String userid);	
	
}
