package com.web.manage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.web.commons.jqgrid.UIPage;
import com.web.commons.util.BeanCopyUtil;
import com.web.manage.dao.ConfigDao;
import com.web.manage.pojo.Config;
import com.web.manage.service.ConfigService;

@Component("configService")
@Transactional
public class ConfigServiceImpl implements ConfigService {
	@Autowired
	private ConfigDao configDao;
	
	@Override
	public Config findById(String id) {
		return configDao.selectByPrimaryKey(id);
	}

	@Override
	public void saveConfig(Config config) {
		config.setUpdatetime(System.currentTimeMillis());
		if (StringUtils.isNotBlank(config.getId())) {
			configDao.updateByPrimaryKeySelective(config);
		} else {
			config.setCreatetime(System.currentTimeMillis());
			configDao.insertSelective(config);
		}
	}

	@Override
	public void deleteConfigById(String id) {
		configDao.deleteByPrimaryKey(id);
	}

	@Override
	public UIPage getPage(Config config, int pageNumber, int pageSize) {
		UIPage page = new UIPage(String.valueOf(pageSize));
		List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
		long count = configDao.selectByStatementCount(config);
		config.setOffset(pageNumber);
		config.setRows(pageSize);
		List<Config> configs = configDao.selectByStatement(config);
		for (Config config2 : configs) {
			Map<String,Object> row=BeanCopyUtil.CopyBeanToMap(config2);
    		rows.add(row);
		}
		page.setRows(rows);
    	page.setRecords(count);
    	return page;
	}

	@Override
	public Config findByUserId(String userid) {
		Config config = new Config();
		config.setUserid(userid);
		List<Config> configs = configDao.selectByStatement(config);
		if (CollectionUtils.isNotEmpty(configs)) {
			return configs.get(0);
		}
		return null;
	}

}
