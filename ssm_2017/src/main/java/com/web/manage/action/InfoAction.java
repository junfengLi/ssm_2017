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
    		if (module.contains("speed")) {
				Speed speed = infoService.findSpeedByInfoId(id);
				model.addAttribute("speed", speed);
			}
    		if (module.contains("online")) {
				Online online = infoService.findOnlineByInfoId(id);
				model.addAttribute("online", online);
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
    
    @RequestMapping(value="/speedSave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> speedSave(Speed speed,
			HttpServletRequest request){
    	Map<String, Object> resultMap = new HashMap<>();
    	String time = request.getParameter("servicetime-d");
//    	 private Long asktime;
//    	    private Long sendmenutime;
//    	    private Long interviewtime;
//    	    private String asktype;
//    	    private Long finshnewstime;
//    	    private Long onlinetime;
//    	    private String source;
//    	    private Long sendneedtime;
//    	    private Long backtime;
    	
    	
    	
    	if (StringUtils.isNotBlank(time)) {
			long servicetime = DateUtil.getLongDateFromString(time);
//			info.setServicetime(servicetime);
		}
//    	infoService.saveInfo(info);
    	resultMap.put("result", true);
    	resultMap.put("success", true);
    	return resultMap;
    }
    
    @RequestMapping(value="/onlineSave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> onlineSave(Online online,
			HttpServletRequest request){
    	Map<String, Object> resultMap = new HashMap<>();
    	String time = request.getParameter("servicetime-d");
    	if (StringUtils.isNotBlank(time)) {
			long servicetime = DateUtil.getLongDateFromString(time);
//			info.setServicetime(servicetime);
		}
//    	infoService.saveInfo(info);
    	resultMap.put("result", true);
    	resultMap.put("success", true);
    	return resultMap;
    }
  
    
}  
