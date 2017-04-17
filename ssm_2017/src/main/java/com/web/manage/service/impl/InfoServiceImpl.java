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
import com.web.commons.util.IsOrEnum;
import com.web.manage.dao.BaseInfoDao;
import com.web.manage.dao.OnlineDao;
import com.web.manage.dao.SpeedDao;
import com.web.manage.pojo.BaseInfo;
import com.web.manage.pojo.Online;
import com.web.manage.pojo.Speed;
import com.web.manage.service.InfoService;

@Component("infoService")
@Transactional  
public class InfoServiceImpl implements InfoService {
	@Autowired 
	private BaseInfoDao infoDao;
	@Autowired
	private SpeedDao speedDao;
	@Autowired
	private OnlineDao onlineDao;
	

	@Override
	public BaseInfo findById(String id) {
		return infoDao.selectByPrimaryKey(id);
	}

	@Override
	public void saveInfo(BaseInfo info) {
		info.setUpdatetime(System.currentTimeMillis());
		if (StringUtils.isNotBlank(info.getId())) {
			infoDao.updateByPrimaryKeySelective(info);
		} else {
			info.setCreatetime(System.currentTimeMillis());
			infoDao.insertSelective(info);
		}
	}

	@Override
	public void deleteInfoById(String id) {
		infoDao.deleteByPrimaryKey(id);
	}

	@Override
	public UIPage getPage(BaseInfo info, int pageNumber, int pageSize) {
		UIPage page = new UIPage();
		List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
		List<BaseInfo> infos = infoDao.selectByStatement(info);
		long count = infoDao.selectByStatementCount(info);
		for (BaseInfo info2 : infos) {
			@SuppressWarnings("unchecked")
			Map<String,Object> row=BeanCopyUtil.CopyBeanToMap(info2);
			Speed speed = speedDao.findByInfoId(info2.getId());
			if (speed != null) {
				row.put("sendmenutime", DateUtil.getFormatDate(speed.getSendmenutime()));
				row.put("finshnewstime", DateUtil.getFormatDate(speed.getFinshnewstime()));
				row.put("backtime", DateUtil.getFormatDate(speed.getBacktime()));
			} else {
				row.put("haveSpeed", IsOrEnum.FOU.getKey());
			}
			
			Online online = onlineDao.findByInfoId(info2.getId());
			if (online != null) {
				row.put("infohref", online.getInfohref());
			} else {
				row.put("haveOnline", IsOrEnum.FOU.getKey());
			}
    		rows.add(row);
		}
		page.setRows(rows);
    	page.setRecords(count);
    	return page;
	}

	@Override
	public Speed findSpeedByInfoId(String infoid) {
		return speedDao.findByInfoId(infoid);
	}

	@Override
	public Online findOnlineByInfoId(String infoid) {
		return onlineDao.findByInfoId(infoid);
	}

	@Override
	public void saveSpeed(Speed speed) {
		if (StringUtils.isNotBlank(speed.getId())) {
			speedDao.updateByPrimaryKeySelective(speed);
		} else {
			speedDao.insertSelective(speed);
		}
	}

	@Override
	public void saveOnline(Online online) {
		if (StringUtils.isNotBlank(online.getId())) {
			onlineDao.updateByPrimaryKeySelective(online);
		} else {
			onlineDao.insertSelective(online);
		}
	}


	

}
