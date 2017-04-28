/**
 * 
 */
package com.wxbaas.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wxbaas.rpc.userinfo.UserInfoService;
import com.wxbaas.rpc.userinfo.model.UserInfoDO;
import com.wxbaas.util.LoggerUtil;
import com.wxbaas.web.base.BaseController;
import com.wxbaas.web.base.ResultInfo;

/**
 * @author zhangxuewen
 *
 */
@Controller
@RequestMapping("/login")
public class LoginContrlloer extends BaseController {
//	@Autowired
//	@Qualifier("userInfoServiceImpl")
//	private UserInfoService userInfoService;
//	
//	
//	final private static Logger logger = LoggerFactory.getLogger(LoginContrlloer.class);
//
//	@RequestMapping(value = { "/api/login" }, method = { RequestMethod.GET, RequestMethod.POST })
//	public void login(HttpServletRequest request, HttpServletResponse response, Model model,LoginInfoForm loginInfo) {
//
//		LoggerUtil.info(logger, "login start");
//		ResultInfo result = new ResultInfo();
//		UserInfoDO  userInfo=userInfoService.getUserInfo(loginInfo.getUsername(),loginInfo.getPassword());
//		if(userInfo!=null){
//			request.getSession().setAttribute("userInfo", userInfo);
//			result.setCode(200);
//			result.setSuccess(true);
//			result.setData(userInfo.getUsername());
//			
//		}else{
//			result.setCode(200);
//			result.setSuccess(false);
//			
//			result.setMessage("error");
//			
//		}
//		
//		this.writeResponseJson(response, result);
//
//	}
//
//	@RequestMapping(value = { "/api/logout" }, method = { RequestMethod.GET,RequestMethod.POST })
//	public String logout(HttpServletRequest request,HttpServletResponse response, Model model) {
//
//	
//		LoggerUtil.info(logger, "login start");
//		request.getSession().setAttribute("userInfo", null);
//		ResultInfo result = new ResultInfo();
//		result.setCode(200);
//		result.setSuccess(true);
//		return "index";
//		
//	}
//
//	@RequestMapping(value = { "/api/getCurrentUser" }, method = { RequestMethod.GET, RequestMethod.POST })
//	public void getCurrentUser(HttpServletRequest request, HttpServletResponse response, Model model) {
//
//			ResultInfo result = new ResultInfo();
//			
//			HttpSession session=request.getSession();
//			
//		    UserInfoDO userInfo= (UserInfoDO) session.getAttribute("userInfo");
//			if(userInfo!=null){
//				result.setCode(200);
//				result.setSuccess(true);
//				result.setData(userInfo.getUsername());
//			}else{
//				result.setCode(200);
//				result.setSuccess(false);
//				
//				result.setMessage("error");
//			}
//	
//
//			this.writeResponseJson(response, result);
//	}
//
//	@RequestMapping(value = { "/" }, method = { RequestMethod.GET,RequestMethod.POST })
//	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
//		
//		return "index";
//	}
//
//	/**
//	 * @return the userInfoService
//	 */
//	public UserInfoService getUserInfoService() {
//		return userInfoService;
//	}
//
//	/**
//	 * @param userInfoService the userInfoService to set
//	 */
//	public void setUserInfoService(UserInfoService userInfoService) {
//		this.userInfoService = userInfoService;
//	}
//
//	
//	

}
