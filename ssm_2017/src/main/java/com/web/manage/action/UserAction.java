package com.web.manage.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.commons.jqgrid.UIPage;
import com.web.manage.pojo.User;
import com.web.manage.service.UserService;

@RequestMapping("/user")  
@Controller
public class UserAction {  
	@Autowired
	private UserService userService;
	
	
	private static final String BASE_PATH = "/manage/sys/user/";

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
    		Model model,HttpServletRequest request,HttpServletResponse response) {  
    	
        return BASE_PATH + module;  
    }
    
    
    
    
    
    
    
    
    
    
    
    //--------------------------------------异步数据---------------------------------------------------
    @RequestMapping("/getPage")
    @ResponseBody
    public UIPage getPage(@RequestParam(value="page",defaultValue="1")int pageNum,
			@RequestParam(value="rows",defaultValue="10")int pageSize,
			HttpServletRequest request){
    	Map<String, Object> searchParams = new HashMap<>();
    	searchParams.put("loginName", "lijunfeng");
    	UIPage page = userService.getPage(searchParams, pageNum, pageSize); 
    	return page;
    }
}  
