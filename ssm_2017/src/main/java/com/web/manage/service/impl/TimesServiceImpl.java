package com.web.manage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.web.commons.jqgrid.UIPage;
import com.web.commons.util.BeanCopyUtil;
import com.web.commons.util.DateUtil;
import com.web.manage.dao.TimesDao;
import com.web.manage.pojo.BaseInfo;
import com.web.manage.pojo.Times;
import com.web.manage.service.TimesService;

@Component("timesService")
@Transactional
public class TimesServiceImpl implements TimesService {
	@Autowired
	private TimesDao timesDao;
	
	@Override
	public Times findById(String id) {
		return timesDao.selectByPrimaryKey(id);
	}

	@Override
	public void saveTimes(Times times) {
		times.setUpdatetime(System.currentTimeMillis());
		if (StringUtils.isNotBlank(times.getId())) {
			timesDao.updateByPrimaryKeySelective(times);
		} else {
			times.setCreatetime(System.currentTimeMillis());
			timesDao.insertSelective(times);
		}
	}

	@Override
	public void deleteTimesById(String id) {
		timesDao.deleteByPrimaryKey(id);
	}

	@Override
	public UIPage getPage(Times times, int pageNumber, int pageSize) {
		UIPage page = new UIPage(String.valueOf(pageSize));
		List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
		long count = timesDao.selectByStatementCount(times);
		times.setOffset(pageNumber);
		times.setRows(pageSize);
		List<Times> timess = timesDao.selectByStatement(times);
		for (Times times2 : timess) {
			Map<String,Object> row=BeanCopyUtil.CopyBeanToMap(times2);
			if (times2.getDate() > 0) {
				row.put("date", DateUtil.getFormatDate(times2.getDate()));
			}
    		rows.add(row);
		}
		page.setRows(rows);
    	page.setRecords(count);
    	return page;
	}

	@Override
	public int reportCount(Times times) {
		times.setOffset(1);
		times.setRows(100);
		List<Times> timesList = timesDao.selectByStatement(times);
		int i = 0;
		for (Times time : timesList) {
			i += time.getTimes();
		}
		return i;
	}

}
