package com.web.manage.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.commons.excel.ExportUtil;
import com.web.commons.jqgrid.UINode;
import com.web.commons.jqgrid.UIPage;
import com.web.commons.util.CommonUtil;
import com.web.commons.util.DateUtil;
import com.web.commons.util.IsOrEnum;
import com.web.manage.pojo.BaseInfo;
import com.web.manage.pojo.BaseInfoResult;
import com.web.manage.pojo.Config;
import com.web.manage.pojo.Online;
import com.web.manage.pojo.Speed;
import com.web.manage.pojo.Times;
import com.web.manage.service.ConfigService;
import com.web.manage.service.InfoService;
import com.web.manage.service.TimesService;
import com.web.manage.util.AskTypeEnum;
import com.web.manage.util.ReportTypeEnum;


@RequestMapping("/project")  
@Controller
public class ProjectAction {  
	@Autowired
	private TimesService timesService;
	@Autowired
	private ConfigService configService;
	@Autowired
	private InfoService infoService;
	private static final String BASE_PATH = "/manage/project/";
   

    /**
     * 页面加载
     * @param module
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/{module}/load")
    @ResponseBody
    public ModelAndView load(@PathVariable("module")String module, 
    		Model model,HttpServletRequest request,HttpServletResponse response) {  
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(BASE_PATH + module);
		String userid = CommonUtil.getLoginName();
    	model.addAttribute("userid", userid);
    	
		if ("index".equals(module)) {
			Config config = configService.findByUserId(userid);
	    	if (config != null) {
	    		String dayConfig = config.getDayconfig();
	    		String weekConfig = config.getWeekconfig();
	    		String monthConfig = config.getMonthconfig();
	    		List<UINode> nodes = ReportTypeEnum.getNodes();
	    		
	    		long starttime = 0, endtime = 0;
	    		Calendar cal = Calendar.getInstance();
	        	cal.setFirstDayOfWeek(Calendar.MONDAY);
	        	cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	          	cal.set(Calendar.HOUR_OF_DAY, 0);
	          	cal.set(Calendar.MINUTE, 0);
	          	cal.set(Calendar.SECOND, 0);
	        	starttime = cal.getTimeInMillis() -1000l;
	        	cal.add(Calendar.WEEK_OF_MONTH, 1);
	        	endtime = cal.getTimeInMillis() -1000l;
//	    		System.out.println(DateUtil.getFormatDateTimeWithoutSecond(starttime));
//	    		System.out.println(DateUtil.getFormatDateTimeWithoutSecond(endtime));
	    		Map<String, String> weekMap = getWeekMap(userid, weekConfig,dayConfig, starttime, endtime);
	    		cal = Calendar.getInstance();
	          	cal.setFirstDayOfWeek(Calendar.MONDAY);
	          	cal.set(Calendar.DAY_OF_MONTH, 1);
	          	cal.set(Calendar.HOUR_OF_DAY, 0);
	          	cal.set(Calendar.MINUTE, 0);
	          	cal.set(Calendar.SECOND, 0);
	          	starttime = cal.getTimeInMillis() -1000l;
	          	cal.add(Calendar.MONTH, 1);
	        	endtime = cal.getTimeInMillis() -1000l;
//	    		System.out.println(DateUtil.getFormatDateTimeWithoutSecond(starttime));
//	    		System.out.println(DateUtil.getFormatDateTimeWithoutSecond(endtime));
	    		Map<String, String> monthMap = getMonthMap(userid, monthConfig, starttime, endtime);
	    		
	    		for (UINode uiNode : nodes) {
	    			String id = uiNode.getId();
	    			//dayConfig = dayConfig.replace("{[" + uiNode.getId() + "]}", "张三，李四");
	    			if (dayConfig.contains("{[" + id + "]}")) {
	    				String value = StringUtils.isBlank(weekMap.get(id)) ? "【暂没有客户】" : weekMap.get(id);
	    				dayConfig = dayConfig.replace("{[" + id + "]}", value);
					}
	    			if (weekConfig.contains("{[" + id + "]}")) {
	    				String value = StringUtils.isBlank(weekMap.get(id)) ? "【暂没有客户】" : weekMap.get(id);
	    				weekConfig = weekConfig.replace("{[" + id + "]}", value);
					}
	    			if (monthConfig.contains("{[" + id + "]}")) {
	    				String value = StringUtils.isBlank(monthMap.get(id)) ? "【暂没有客户】" : monthMap.get(id);
	    				monthConfig = monthConfig.replace("{[" + id + "]}", value);
					}
				}
	    		config.setDayconfig(dayConfig);
	    		config.setWeekconfig(weekConfig);
	    		config.setMonthconfig(monthConfig);
				model.addAttribute("config", config);
			}
		}
		
		modelAndView.addObject(model);
		
		return modelAndView;
	}
  public static void main(String[] args) {
	  Calendar cal = Calendar.getInstance();
  	cal.setFirstDayOfWeek(Calendar.MONDAY);
  	cal.set(Calendar.DAY_OF_MONTH, 1);
  	cal.set(Calendar.HOUR_OF_DAY, 0);
  	cal.set(Calendar.MINUTE, 0);
  	cal.set(Calendar.SECOND, 0);
  	long starttime = cal.getTimeInMillis();
  	cal.add(Calendar.MONTH, 1);
	long endtime = cal.getTimeInMillis();
  	System.out.println(DateUtil.getFormatDateTime(starttime));
  	System.out.println(DateUtil.getFormatDateTime(endtime));
}

    private Map<String, String> getWeekMap(String userid, String weekConfig, String dayConfig, long starttime, long endtime) {
    	
    	Map<String, String> weekMap = new HashMap<>();
    	BaseInfoResult info = new BaseInfoResult();
    	StringBuilder key = new StringBuilder();
    	key = new StringBuilder(ReportTypeEnum.SENDMENU.getKey());
    	if (weekConfig.contains("{[" + key.toString() + "]}") || dayConfig.contains("{[" + key.toString() + "]}")) {
    		info = null;
    		info = new BaseInfoResult();
    		info.setUserid(userid);
    		info.setSendmenutime1(starttime);
    		info.setSendmenutime2(endtime);
    		weekMap.put(key.toString(), String.valueOf(infoService.reportCount(info)));
		}
    	key = new StringBuilder(ReportTypeEnum.INTERVIEW.getKey());
    	if (weekConfig.contains("{[" + key.toString() + "]}") || dayConfig.contains("{[" + key.toString() + "]}")) {
    		info = null;
    		info = new BaseInfoResult();
    		info.setUserid(userid);
    		info.setInterviewtime1(starttime);
    		info.setInterviewtime2(endtime);
    		weekMap.put(key.toString(), String.valueOf(infoService.reportCount(info)));
		}
    	key = new StringBuilder(ReportTypeEnum.FINISH.getKey());
    	if (weekConfig.contains("{[" + key.toString() + "]}") || dayConfig.contains("{[" + key.toString() + "]}")) {
    		info = null;
    		info = new BaseInfoResult();
    		info.setUserid(userid);
    		info.setFinshnewstime1(starttime);
    		info.setFinshnewstime2(endtime);
    		weekMap.put(key.toString(), String.valueOf(infoService.reportCount(info)));
		}
    	key = new StringBuilder(ReportTypeEnum.BACK.getKey());
    	if (weekConfig.contains("{[" + key.toString() + "]}") || dayConfig.contains("{[" + key.toString() + "]}")) {
    		info = null;
    		info = new BaseInfoResult();
    		info.setUserid(userid);
    		info.setBacktime1(starttime);
    		info.setBacktime2(endtime);
    		weekMap.put(key.toString(), String.valueOf(infoService.reportCount(info)));
		}
    	key = new StringBuilder(ReportTypeEnum.ONLINE.getKey());
    	if (weekConfig.contains("{[" + key.toString() + "]}") || dayConfig.contains("{[" + key.toString() + "]}")) {
    		info = null;
    		info = new BaseInfoResult();
    		info.setUserid(userid);
    		info.setOnlinetime1(starttime);
    		info.setOnlinetime2(endtime);
    		weekMap.put(key.toString(), String.valueOf(infoService.reportCount(info)));
		}
    	key = new StringBuilder(ReportTypeEnum.SCORE.getKey());
    	if (weekConfig.contains("{[" + key.toString() + "]}") || dayConfig.contains("{[" + key.toString() + "]}")) {
    		info = null;
    		info = new BaseInfoResult();
    		info.setUserid(userid);
    		info.setIsmark(IsOrEnum.SHI.getKey());
    		info.setOnlinetime1(starttime);
    		info.setOnlinetime2(endtime);
    		weekMap.put(key.toString(), String.valueOf(infoService.reportCount(info)));
		}
    	key = new StringBuilder(ReportTypeEnum.ASK.getKey());
    	if (weekConfig.contains("{[" + key.toString() + "]}") || dayConfig.contains("{[" + key.toString() + "]}")) {
    		Times times = new Times();
    		times.setUserid(userid);
    		times.setDate1(starttime);
    		times.setDate2(endtime);
    		weekMap.put(key.toString(), String.valueOf(timesService.reportCount(times)));
		}
    	key = new StringBuilder(ReportTypeEnum.FINISHSEND.getKey());
    	if (weekConfig.contains("{[" + key.toString() + "]}") || dayConfig.contains("{[" + key.toString() + "]}")) {
    		info = null;
    		info = new BaseInfoResult();
    		info.setUserid(userid);
    		info.setFinshnewstime1(starttime);
    		info.setFinshnewstime2(endtime);
    		info.setOnlinetime(0l);
    		weekMap.put(key.toString(), String.valueOf(infoService.reportName(info)));
		}
    	key = new StringBuilder(ReportTypeEnum.SENDINTERVIEW.getKey());
    	if (weekConfig.contains("{[" + key.toString() + "]}") || dayConfig.contains("{[" + key.toString() + "]}")) {
    		info = null;
    		info = new BaseInfoResult();
    		info.setUserid(userid);
    		info.setInterviewtime1(starttime);
    		info.setInterviewtime2(endtime);
    		info.setFinshnewstime(0l);
    		String names = String.valueOf(infoService.reportName(info));
    		weekMap.put(key.toString(), names);
		}
    	key = new StringBuilder(ReportTypeEnum.ONLYSEND.getKey());
    	if (weekConfig.contains("{[" + key.toString() + "]}") || dayConfig.contains("{[" + key.toString() + "]}")) {
    		info = null;
    		info = new BaseInfoResult();
    		info.setUserid(userid);
    		info.setSendmenutime1(starttime);
    		info.setSendmenutime2(endtime);
    		info.setInterviewtime(0l);
    		weekMap.put(key.toString(), String.valueOf(infoService.reportName(info)));
		}
		return weekMap;
	}

    private Map<String, String> getMonthMap(String userid, String monthConfig, long starttime, long endtime) {
    	
		Map<String, String> monthMap = new HashMap<>();
		BaseInfoResult info = new BaseInfoResult();
    	StringBuilder key = new StringBuilder();
    	key = new StringBuilder(ReportTypeEnum.SENDMENU.getKey());
    	if (monthConfig.contains("{[" + key.toString() + "]}")) {
    		info = null;
    		info = new BaseInfoResult();
    		info.setUserid(userid);
    		info.setSendmenutime1(starttime);
    		info.setSendmenutime2(endtime);
    		monthMap.put(key.toString(), String.valueOf(infoService.reportCount(info)));
		}
    	key = new StringBuilder(ReportTypeEnum.INTERVIEW.getKey());
    	if (monthConfig.contains("{[" + key.toString() + "]}")) {
    		info = null;
    		info = new BaseInfoResult();
    		info.setUserid(userid);
    		info.setInterviewtime1(starttime);
    		info.setInterviewtime2(endtime);
    		monthMap.put(key.toString(), String.valueOf(infoService.reportCount(info)));
		}
    	key = new StringBuilder(ReportTypeEnum.FINISH.getKey());
    	if (monthConfig.contains("{[" + key.toString() + "]}")) {
    		info = null;
    		info = new BaseInfoResult();
    		info.setUserid(userid);
    		info.setFinshnewstime1(starttime);
    		info.setFinshnewstime2(endtime);
    		monthMap.put(key.toString(), String.valueOf(infoService.reportCount(info)));
		}
    	key = new StringBuilder(ReportTypeEnum.BACK.getKey());
    	if (monthConfig.contains("{[" + key.toString() + "]}")) {
    		info = null;
    		info = new BaseInfoResult();
    		info.setUserid(userid);
    		info.setBacktime1(starttime);
    		info.setBacktime2(endtime);
    		monthMap.put(key.toString(), String.valueOf(infoService.reportCount(info)));
		}
    	key = new StringBuilder(ReportTypeEnum.ONLINE.getKey());
    	if (monthConfig.contains("{[" + key.toString() + "]}")) {
    		info = null;
    		info = new BaseInfoResult();
    		info.setUserid(userid);
    		info.setOnlinetime1(starttime);
    		info.setOnlinetime2(endtime);
    		monthMap.put(key.toString(), String.valueOf(infoService.reportCount(info)));
		}
    	key = new StringBuilder(ReportTypeEnum.SCORE.getKey());
    	if (monthConfig.contains("{[" + key.toString() + "]}")) {
    		info = null;
    		info = new BaseInfoResult();
    		info.setUserid(userid);
    		info.setIsmark(IsOrEnum.SHI.getKey());
    		info.setOnlinetime1(starttime);
    		info.setOnlinetime2(endtime);
    		monthMap.put(key.toString(), String.valueOf(infoService.reportCount(info)));
		}
    	key = new StringBuilder(ReportTypeEnum.ASK.getKey());
    	if (monthConfig.contains("{[" + key.toString() + "]}")) {
    		Times times = new Times();
    		times.setUserid(userid);
    		times.setDate1(starttime);
    		times.setDate2(endtime);
    		monthMap.put(key.toString(), String.valueOf(timesService.reportCount(times)));
		}
    	key = new StringBuilder(ReportTypeEnum.FINISHSEND.getKey());
    	if (monthConfig.contains("{[" + key.toString() + "]}")) {
    		info = null;
    		info = new BaseInfoResult();
    		info.setUserid(userid);
    		info.setFinshnewstime1(starttime);
    		info.setFinshnewstime2(endtime);
    		info.setOnlinetime(0l);
    		monthMap.put(key.toString(), String.valueOf(infoService.reportName(info)));
		}
    	key = new StringBuilder(ReportTypeEnum.SENDINTERVIEW.getKey());
    	if (monthConfig.contains("{[" + key.toString() + "]}")) {
    		info = null;
    		info = new BaseInfoResult();
    		info.setUserid(userid);
    		info.setInterviewtime1(starttime);
    		info.setInterviewtime2(endtime);
    		info.setFinshnewstime(0l);
    		monthMap.put(key.toString(), String.valueOf(infoService.reportName(info)));
		}
    	key = new StringBuilder(ReportTypeEnum.ONLYSEND.getKey());
    	if (monthConfig.contains("{[" + key.toString() + "]}")) {
    		info = null;
    		info = new BaseInfoResult();
    		info.setUserid(userid);
    		info.setSendmenutime1(starttime);
    		info.setSendmenutime2(endtime);
    		monthMap.put(key.toString(), String.valueOf(infoService.reportName(info)));
		}
		
		return monthMap;
	}

	/**
     * 窗口页面跳转
     * @param module
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/{module}/forward")
    public String forward(@PathVariable("module")String module,
    		@RequestParam(value="id",defaultValue="")String id,
    		Model model,HttpServletRequest request,HttpServletResponse response) {
    	String userid = CommonUtil.getLoginName();
    	model.addAttribute("userid", userid);
    	if ("config".equals(module)) {
    		model.addAttribute("nodes", ReportTypeEnum.getNodes());
			Config config = configService.findById(id);
			model.addAttribute("config", config);
		} else if ("times".equals(module)) {
			Times times = timesService.findById(id);
			model.addAttribute("times", times);
			if (times != null) {
				if (times.getDate() > 0) {
					model.addAttribute("date", DateUtil.getFormatDate(times.getDate()));
				}
			}
		}
        return BASE_PATH + module;  
    }
    
    
    @RequestMapping(value="/config/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Config config,
			HttpServletRequest request){
    	Map<String, Object> resultMap = new HashMap<>();
    	configService.saveConfig(config);
    	resultMap.put("result", true);
    	resultMap.put("success", true);
    	return resultMap;
    }
    
    @RequestMapping(value="/times/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Times times,
			HttpServletRequest request){
    	Map<String, Object> resultMap = new HashMap<>();
    	String date = request.getParameter("dated");
    	times.setDate(0l);
    	if (StringUtils.isNotBlank(date)) {
			long datetime = DateUtil.getLongDateFromString(date);
			times.setDate(datetime);
		}
    	timesService.saveTimes(times);
    	resultMap.put("result", true);
    	resultMap.put("success", true);
    	return resultMap;
    }
    
    @RequestMapping(value="/timesdelete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> timesdelete(@RequestParam(value="id",defaultValue="")String id,
			HttpServletRequest request){
    	Map<String, Object> resultMap = new HashMap<>();
    	timesService.deleteTimesById(id);
    	resultMap.put("result", true);
    	return resultMap;
    }
    
    @RequestMapping(value="/monthSearch", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> monthSearch(@RequestParam(value="time",defaultValue="")String time,
			HttpServletRequest request){
    	Map<String, Object> resultMap = new HashMap<>();
    	
    	if (DateUtil.getLongDateFromString(time, false) == 0) return resultMap;
    	
		String userid = CommonUtil.getLoginName();
		Config config = configService.findByUserId(userid);
    	if (config != null) {
    		String monthConfig = config.getMonthconfig();
    		List<UINode> nodes = ReportTypeEnum.getNodes();
    		long times = DateUtil.getLongDateFromString(time, false);
        	Calendar cal = Calendar.getInstance();
    		cal.setTimeInMillis(times);
          	long starttime = 0, endtime = 0;
        	starttime = times -1000l;
        	cal.add(Calendar.MONTH, 1);
        	endtime = cal.getTimeInMillis() -1000l;
//    		System.out.println(DateUtil.getFormatDateTime(starttime));
//    		System.out.println(DateUtil.getFormatDateTime(endtime));
    		Map<String, String> monthMap = getMonthMap(userid, monthConfig, starttime, endtime);
    		for (UINode uiNode : nodes) {
    			String id = uiNode.getId();
    			if (monthConfig.contains("{[" + id + "]}")) {
    				String value = StringUtils.isBlank(monthMap.get(id)) ? "【暂没有客户】" : monthMap.get(id);
    				monthConfig = monthConfig.replace("{[" + id + "]}", value);
				}
			}
    		resultMap.put("month", monthConfig);
    	}
		
    	resultMap.put("result", true);
    	return resultMap;
    }
    
    
    @RequestMapping(value="/weekSearch", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> weekSearch(@RequestParam(value="time",defaultValue="")String time,
			HttpServletRequest request){
    	Map<String, Object> resultMap = new HashMap<>();
    	if (DateUtil.getLongDateFromString(time, false) == 0) return resultMap;
    	
		String userid = CommonUtil.getLoginName();
		Config config = configService.findByUserId(userid);
    	if (config != null) {
    		String dayConfig = config.getDayconfig();
    		String weekConfig = config.getWeekconfig();
    		List<UINode> nodes = ReportTypeEnum.getNodes();
    		long times = DateUtil.getLongDateFromString(time);
        	Calendar cal = Calendar.getInstance();
    		cal.setTimeInMillis(times);
          	long starttime = 0, endtime = 0;
          	cal.setFirstDayOfWeek(Calendar.MONDAY);
        	cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
          	starttime = cal.getTimeInMillis() -1000l;
        	cal.add(Calendar.WEEK_OF_MONTH, 1);
        	endtime = cal.getTimeInMillis() -1000l;
//    		System.out.println(DateUtil.getFormatDateTimeWithoutSecond(starttime));
//    		System.out.println(DateUtil.getFormatDateTimeWithoutSecond(endtime));
    		Map<String, String> weekMap = getWeekMap(userid, weekConfig,dayConfig, starttime, endtime);	
    		for (UINode uiNode : nodes) {
    			String id = uiNode.getId();
    			if (dayConfig.contains("{[" + id + "]}")) {
    				String value = StringUtils.isBlank(weekMap.get(id)) ? "【暂没有客户】" : weekMap.get(id);
    				dayConfig = dayConfig.replace("{[" + id + "]}", value);
				}
    			if (weekConfig.contains("{[" + id + "]}")) {
    				String value = StringUtils.isBlank(weekMap.get(id)) ? "【暂没有客户】" : weekMap.get(id);
    				weekConfig = weekConfig.replace("{[" + id + "]}", value);
				}
			}
    		resultMap.put("day", dayConfig);
    		resultMap.put("week", weekConfig);
    	}
    	resultMap.put("result", true);
    	return resultMap;
    }
    
    
    @RequestMapping(value = "/doExport", method = RequestMethod.GET)
	public void doExport(HttpServletRequest request, HttpServletResponse response){
    	String userid = CommonUtil.getLoginName();
		if (StringUtils.isNotBlank(userid)) {
			List<String[]> datas = new ArrayList<String[]>();
			BaseInfo info = new BaseInfo();
			info.setUserid(userid);
			List<BaseInfo> baseInfos = infoService.findList(info);
			for (BaseInfo baseInfo : baseInfos) {
				String[] data = new String[24];
				//姓名
				data[0] = StringUtils.trimToEmpty(baseInfo.getName());
				//电话
				data[1] = StringUtils.trimToEmpty(baseInfo.getMobile());
				//备用电话
				data[2] = StringUtils.trimToEmpty(baseInfo.getMobile2());
				//邮箱
				data[3] = StringUtils.trimToEmpty(baseInfo.getEmail());
				//备用邮箱
				data[4] = StringUtils.trimToEmpty(baseInfo.getEmail2());
				//项目名称
				data[5] = StringUtils.trimToEmpty(baseInfo.getProjectname());
				//项目链接
				data[6] = StringUtils.trimToEmpty(baseInfo.getProjecthref());
				//客服
				data[7] = StringUtils.trimToEmpty(baseInfo.getAsker());
				//服务到期时间
				if (baseInfo.getServicetime() > 0) {
					data[8] = DateUtil.getFormatDate(baseInfo.getServicetime());					
				} else {
					data[8] = "";
				}
				Speed speed = baseInfo.getInfospeed();
				if (speed != null) {
					//联系客户时间
					data[9] = StringUtils.trimToEmpty(speed.getAsktime());					
					//发送采访提纲时间
					if (speed.getSendmenutime() > 0) {
						data[10] = DateUtil.getFormatDate(speed.getSendmenutime());					
					} else {
						data[10] = "";
					}
					//采访时间
					if (speed.getInterviewtime() > 0) {
						data[11] = DateUtil.getFormatDate(speed.getInterviewtime());					
					} 
					//采访类型
					data[12] = AskTypeEnum.getDescByKey(speed.getAsktype());					
					//成稿时间
					if (speed.getFinshnewstime() > 0) {
						data[13] = DateUtil.getFormatDate(speed.getFinshnewstime());					
					} 
					//客户确认上线时间
					if (speed.getOnlinetime() > 0) {
						data[14] = DateUtil.getFormatDate(speed.getOnlinetime());					
					} 
					//发送设计需求时间
					if (speed.getSendneedtime() > 0) {
						data[15] = DateUtil.getFormatDate(speed.getSendneedtime());					
					}
					//发送反馈时间
					if (speed.getBacktime() > 0) {
						data[16] = DateUtil.getFormatDate(speed.getBacktime());					
					}
				} else {
					
				}
				Online online = baseInfo.getInfoonline();
				if (online != null) {
					//编辑后台时间
					if (online.getEditbackgroundtime() > 0) {
						data[17] = DateUtil.getFormatDate(online.getEditbackgroundtime());					
					}
					//推首时间
					if (online.getPushheadtime() > 0) {
						data[17] = DateUtil.getFormatDate(online.getPushheadtime());					
					}
					//资讯轮播时间
					if (online.getInformationtime() > 0) {
						data[17] = DateUtil.getFormatDate(online.getInformationtime());					
					}
					//项目集合时间
					if (online.getItemsettime() > 0) {
						data[17] = DateUtil.getFormatDate(online.getItemsettime());					
					}
					//首页banner时间
					if (online.getBannertime() > 0) {
						data[17] = DateUtil.getFormatDate(online.getBannertime());					
					}
					//文章链接
					data[22] = StringUtils.trimToEmpty(online.getInfohref());
				}
				//备注
				data[23] = StringUtils.trimToEmpty(baseInfo.getNote());
				datas.add(data);
			}
			ExportUtil.doExportFromRow(request, response, datas, "/excelXml/info.xls", "批量导出", 3);
		}
			
	}
    
    //--------------------------------------异步数据---------------------------------------------------
    @RequestMapping("/getPage")
    @ResponseBody
    public UIPage getPage(Times times,@RequestParam(value="page",defaultValue="1")int pageNum,
			@RequestParam(value="rows",defaultValue="10")int pageSize,
			HttpServletRequest request){
    	//如果不传用户ID，返回空
    	if (StringUtils.isBlank(times.getUserid())) new UIPage();
    	
    	UIPage page = timesService.getPage(times, pageNum, pageSize); 
    	return page;
    }
}  