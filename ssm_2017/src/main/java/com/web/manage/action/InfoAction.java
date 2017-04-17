package com.web.manage.action;

import java.util.HashMap;
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

import com.web.commons.jqgrid.UIPage;
import com.web.commons.util.CommonUtil;
import com.web.commons.util.DateUtil;
import com.web.manage.pojo.BaseInfo;
import com.web.manage.pojo.Online;
import com.web.manage.pojo.Speed;
import com.web.manage.service.InfoService;

@RequestMapping("/info")  
@Controller
public class InfoAction {  
	@Autowired
	private InfoService infoService;
	
	
	private static final String BASE_PATH = "/manage/info/";

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
		//-------------------业务数据------------------------
		model.addAttribute("userid", CommonUtil.getLoginName());		
		
		//-------------------业务数据-------------------------
		modelAndView.addObject(model);
		return modelAndView;
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
    	model.addAttribute("userid", CommonUtil.getLoginName());
    	if (StringUtils.isNotBlank(id)) {
    		BaseInfo info = infoService.findById(id);
    		model.addAttribute("info", info);
    		if (info.getServicetime() > 0) {
    			model.addAttribute("servicetime", DateUtil.getFormatDate(info.getServicetime()));
			}
    		if (module.contains("Speed")) {
				Speed speed = infoService.findSpeedByInfoId(id);
				model.addAttribute("speed", speed);
				if (speed != null) {
					if (speed.getAsktime() > 0) {
						model.addAttribute("asktime", DateUtil.getFormatDate(speed.getAsktime()));
					}
					if (speed.getSendmenutime() > 0) {
						model.addAttribute("sendmenutime", DateUtil.getFormatDate(speed.getSendmenutime()));
					}
					if (speed.getInterviewtime() > 0) {
						model.addAttribute("interviewtime", DateUtil.getFormatDate(speed.getInterviewtime()));
					}
					if (speed.getFinshnewstime() > 0) {
						model.addAttribute("finshnewstime", DateUtil.getFormatDate(speed.getFinshnewstime()));
					}
					if (speed.getOnlinetime() > 0) {
						model.addAttribute("onlinetime", DateUtil.getFormatDate(speed.getOnlinetime()));
					}
					if (speed.getSendneedtime() > 0) {
						model.addAttribute("sendneedtime", DateUtil.getFormatDate(speed.getSendneedtime()));
					}
					if (speed.getBacktime() > 0) {
						model.addAttribute("backtime", DateUtil.getFormatDate(speed.getBacktime()));
					}
				}
			}
    		if (module.contains("Online")) {
				Online online = infoService.findOnlineByInfoId(id);
				model.addAttribute("online", online);
				if (online != null) {
					if (online.getEditbackgroundtime() > 0) {
						model.addAttribute("editbackgroundtime", DateUtil.getFormatDate(online.getEditbackgroundtime()));
					}
					if (online.getPushheadtime() > 0) {
						model.addAttribute("pushheadtime", DateUtil.getFormatDate(online.getPushheadtime()));
					}
					if (online.getInformationtime() > 0) {
						model.addAttribute("informationtime", DateUtil.getFormatDate(online.getInformationtime()));
					}
					if (online.getItemsettime() > 0) {
						model.addAttribute("itemsettime", DateUtil.getFormatDate(online.getItemsettime()));
					}
					if (online.getBannertime() > 0) {
						model.addAttribute("bannertime", DateUtil.getFormatDate(online.getBannertime()));
					}
				}
			}
    		
		}
        return BASE_PATH + module;  
    }
    
    
    
    
    
    
    
    
    
    
    
    //--------------------------------------异步数据---------------------------------------------------
    @RequestMapping("/getPage")
    @ResponseBody
    public UIPage getPage(BaseInfo info,@RequestParam(value="page",defaultValue="1")int pageNum,
			@RequestParam(value="rows",defaultValue="10")int pageSize,
			HttpServletRequest request){
    	UIPage page = infoService.getPage(info, pageNum, pageSize); 
    	return page;
    }
    
    @RequestMapping(value="/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(BaseInfo info,
			HttpServletRequest request){
    	Map<String, Object> resultMap = new HashMap<>();
    	String time = request.getParameter("servicetime-d");
    	if (StringUtils.isNotBlank(time)) {
			long servicetime = DateUtil.getLongDateFromString(time);
			info.setServicetime(servicetime);
		}
    	infoService.saveInfo(info);
    	resultMap.put("result", true);
    	resultMap.put("success", true);
    	return resultMap;
    }
    
    @RequestMapping(value="/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value="id",defaultValue="")String id,
			HttpServletRequest request){
    	Map<String, Object> resultMap = new HashMap<>();
    	infoService.deleteInfoById(id);
    	infoService.deleteSpeedById(id);
    	infoService.deleteOnlineById(id);
    	resultMap.put("result", true);
    	return resultMap;
    }
    
    @RequestMapping(value="/speedSave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> speedSave(Speed speed,
			HttpServletRequest request){
    	Map<String, Object> resultMap = new HashMap<>();
    	String timeasktime = request.getParameter("asktime-d");
    	String timesendmenutime = request.getParameter("sendmenutime-d");
    	String timeinterviewtime = request.getParameter("interviewtime-d");
    	String timefinshnewstime = request.getParameter("finshnewstime-d");
    	String timeonlinetime = request.getParameter("onlinetime-d");
    	String timesendneedtime = request.getParameter("sendneedtime-d");
    	String timebacktime = request.getParameter("backtime-d");
    	
    	
    	if (StringUtils.isNotBlank(timeasktime)) {
			long asktime = DateUtil.getLongDateFromString(timeasktime);
			speed.setAsktime(asktime);
		}
    	if (StringUtils.isNotBlank(timesendmenutime)) {
			long sendmenutime = DateUtil.getLongDateFromString(timesendmenutime);
			speed.setSendmenutime(sendmenutime);
		}
    	if (StringUtils.isNotBlank(timeinterviewtime)) {
			long interviewtime = DateUtil.getLongDateFromString(timeinterviewtime);
			speed.setInterviewtime(interviewtime);;
		}
    	if (StringUtils.isNotBlank(timefinshnewstime)) {
			long finshnewstime = DateUtil.getLongDateFromString(timefinshnewstime);
			speed.setFinshnewstime(finshnewstime);
		}
    	if (StringUtils.isNotBlank(timeonlinetime)) {
			long onlinetime = DateUtil.getLongDateFromString(timeonlinetime);
			speed.setOnlinetime(onlinetime);
		}
    	if (StringUtils.isNotBlank(timesendneedtime)) {
			long sendneedtime = DateUtil.getLongDateFromString(timesendneedtime);
			speed.setSendneedtime(sendneedtime);
		}
    	if (StringUtils.isNotBlank(timebacktime)) {
			long backtime = DateUtil.getLongDateFromString(timebacktime);
			speed.setBacktime(backtime);
		}
    	infoService.saveSpeed(speed);
    	resultMap.put("result", true);
    	resultMap.put("success", true);
    	return resultMap;
    }
    
    @RequestMapping(value="/onlineSave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> onlineSave(Online online,
			HttpServletRequest request){
    	Map<String, Object> resultMap = new HashMap<>();
    	String timeeditbackgroundtime = request.getParameter("editbackgroundtime-d");
    	String timepushheadtime = request.getParameter("pushheadtime-d");
    	String timeinformationtime = request.getParameter("informationtime-d");
    	String timeitemsettime = request.getParameter("itemsettime-d");
    	String timebannertime = request.getParameter("bannertime-d");
    	
    	if (StringUtils.isNotBlank(timeeditbackgroundtime)) {
			long editbackgroundtime = DateUtil.getLongDateFromString(timeeditbackgroundtime);
			online.setEditbackgroundtime(editbackgroundtime);
		}
    	if (StringUtils.isNotBlank(timepushheadtime)) {
			long pushheadtime = DateUtil.getLongDateFromString(timepushheadtime);
			online.setPushheadtime(pushheadtime);
		}
    	if (StringUtils.isNotBlank(timeinformationtime)) {
			long informationtime = DateUtil.getLongDateFromString(timeinformationtime);
			online.setInformationtime(informationtime);
		}
    	if (StringUtils.isNotBlank(timeitemsettime)) {
			long itemsettime = DateUtil.getLongDateFromString(timeitemsettime);
			online.setItemsettime(itemsettime);
		}
    	if (StringUtils.isNotBlank(timebannertime)) {
			long bannertime = DateUtil.getLongDateFromString(timebannertime);
			online.setBannertime(bannertime);
		}
    	
    	infoService.saveOnline(online);
    	resultMap.put("result", true);
    	resultMap.put("success", true);
    	return resultMap;
    }
  
    
}  
