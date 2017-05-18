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
import com.web.manage.pojo.BaseInfoResult;
import com.web.manage.pojo.Online;
import com.web.manage.pojo.Speed;
import com.web.manage.service.InfoService;
import com.web.manage.util.SpeedTypeEnum;

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
		UIPage page = new UIPage(String.valueOf(pageSize));
		List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
		long count = infoDao.selectByStatementCount(info);
		info.setOffset(pageNumber);
		info.setRows(pageSize);
		List<BaseInfo> infos = infoDao.selectByStatement(info);
		setMaps(infos, rows);
		page.setRows(rows);
    	page.setRecords(count);
    	return page;
	}

	
	@Override
	public UIPage getPage(BaseInfoResult info, int pageNumber, int pageSize) {
		UIPage page = new UIPage(String.valueOf(pageSize));
		List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
		long count = infoDao.selectByStatementForResultCount(info);
		info.setOffset(pageNumber);
		info.setRows(pageSize);
		List<BaseInfo> infos = infoDao.selectByStatementForResult(info);
		setMaps(infos, rows);
		page.setRows(rows);
    	page.setRecords(count);
    	return page;
	}
	
	@Override
	public List<BaseInfo> findList(BaseInfo info) {
		return infoDao.selectByStatementForExport(info);
	}
	
	private void setMaps(List<BaseInfo> infos, List<Map<String, Object>> rows){
		for (BaseInfo info2 : infos) {
			@SuppressWarnings("unchecked")
			Map<String,Object> row=BeanCopyUtil.CopyBeanToMap(info2);
			Speed speed = speedDao.findByInfoId(info2.getId());
			if (speed != null) {
				row.put("source", speed.getSource());
				if (speed.getInterviewtime() > 0) {
					row.put("interviewtime", DateUtil.getFormatDate(speed.getInterviewtime()));
				}
				if (speed.getSendmenutime() > 0) {
					row.put("sendmenutime", DateUtil.getFormatDate(speed.getSendmenutime()));
				}
				if (speed.getFinshnewstime() > 0) {
					row.put("finshnewstime", DateUtil.getFormatDate(speed.getFinshnewstime()));
				}
				if (speed.getBacktime() > 0) {
					row.put("backtime", DateUtil.getFormatDate(speed.getBacktime()));
				}
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
		BaseInfo info = infoDao.selectByPrimaryKey(speed.getInfoid());
		//记录采访进度
		if (info != null) {
			if (speed.getOnlinetime()!=null && speed.getOnlinetime()>0 && StringUtils.isNotBlank(speed.getSource())) {
				info.setIsmark(IsOrEnum.SHI.getKey());
			} 
			
			if (speed.getBacktime()!=null && speed.getBacktime()>0) {
				info.setSpeed(SpeedTypeEnum.FSFK.getKey());
			//} else if (StringUtils.isNotBlank(speed.getSource())) {
				//info.setSpeed(SpeedTypeEnum.DF.getKey());
			} else if (speed.getFinshnewstime()!=null && speed.getFinshnewstime()>0) {
				info.setSpeed(SpeedTypeEnum.CG.getKey());
			} else if (speed.getInterviewtime() !=null &&speed.getInterviewtime()>0 ) {
				info.setSpeed(SpeedTypeEnum.CF.getKey());
			} else if (speed.getSendmenutime() !=null && speed.getSendmenutime()>0 ) {
				info.setSpeed(SpeedTypeEnum.FSCFTG.getKey());
			}
			infoDao.updateByPrimaryKeySelective(info);
		}
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

	@Override
	public void deleteSpeedById(String id) {
		speedDao.deleteByInfoId(id);
	}

	@Override
	public void deleteOnlineById(String id) {
		onlineDao.deleteByInfoId(id);
	}

	@Override
	public int reportCount(BaseInfoResult info) {
		return infoDao.selectByStatementForResultCount(info);
	}

	@Override
	public String reportName(BaseInfoResult info) {
		info.setOffset(1);
		info.setRows(100);
		List<BaseInfo> infos = infoDao.selectByStatementForResult(info);
		StringBuffer names = new StringBuffer();
		int i = 0;
		for (BaseInfo baseInfo : infos) {
			if (i == 0) {
				names.append(baseInfo.getName());
				i++;
			} else {
				names.append("，" + baseInfo.getName());
			}
		}
		return names.toString();
	}


	

}
