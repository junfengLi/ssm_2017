package com.web.manage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.commons.shiro.code.CheckCodeUtil;

/**
 * 处理login 控制，已经整合了shiro，因此将login、logout交给了shiro 这个action 只完成错误信息的跳转和get请求的跳转
 */
@Controller
public class LoginAction {

	Logger logger = Logger.getLogger(LoginAction.class);
	
	
	/**
     * 生成验证码
     * @param request
     * @param response
     */
    @RequestMapping("/imageCode") 
    @ResponseBody
    public void getImageCode(HttpServletRequest request, HttpServletResponse response){
    	CheckCodeUtil.getImageCode(request, response);
    }
	/**
	 * LOGIN GET请求
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request,Model model) {
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated())
			subject.logout();
		
		String pageUri = "/login";
		return pageUri;
	}
	

	/**
	 * 登录失败后的信息跳转
	 * 
	 * @param username
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String fail(
			@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName,
			@RequestParam(FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM) String password,
			@RequestParam(value = "imageCode", defaultValue = "") String imageCode,
			Model model, HttpServletRequest request) {
		String error = "";
		Object shiroFail = request.getAttribute("shiroLoginFailure");
		boolean checkImageCode = true;
//		checkImageCode = CheckCodeUtil.checkImageCode(request, imageCode);
		if (checkImageCode) {
			if (shiroFail != null) {
				if(shiroFail.equals(IncorrectCredentialsException.class.getName())){
					error = "密码错误";
				}else if(shiroFail.equals(UnknownAccountException.class.getName())){
					error = "账号或密码错误";
				}else if(shiroFail.equals(LockedAccountException.class.getName())){
					error = "账号被锁定";
				}else if(shiroFail.equals(AuthenticationException.class.getName())){
					error = "账号认证出错";
				}else {
					error = "未知错误";
				}
			}
		} else {
			error = "验证码错误";
		}
		
		model.addAttribute("error", error);
		if(StringUtils.isNotEmpty(error)){
			logger.info("登录异常："+error+"\r\n"+shiroFail);
			String pageUri = "/login";
			return pageUri;
		}else{
			return "redirect:/manage";
		}
	}
	
}
