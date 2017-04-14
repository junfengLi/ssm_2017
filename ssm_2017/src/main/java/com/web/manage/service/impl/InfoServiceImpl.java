package com.web.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.web.commons.jqgrid.UIPage;
import com.web.manage.dao.InfoDao;
import com.web.manage.pojo.Info;
import com.web.manage.service.InfoService;

@Component("infoService")
@Transactional  
public class InfoServiceImpl implements InfoService {
	@Autowired 
	private InfoDao infoDao;

	@Override
	public Info findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveInfo(Info info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInfoById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UIPage getPage(Info info, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
