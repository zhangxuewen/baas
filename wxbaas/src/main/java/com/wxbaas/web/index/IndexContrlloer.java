/**
 * 
 */
package com.wxbaas.web.index;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wxbaas.web.base.BaseController;

/**
 * @author zhangxuewen
 *
 */
@Controller

public class IndexContrlloer extends BaseController {

	@RequestMapping(value = { "/" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {

		return "index";
	}
	// @Autowired
	// @Qualifier("userInfoServiceImpl")
	// private UserInfoService userInfoService;
	//
	//
	// final private static Logger logger =
	// LoggerFactory.getLogger(UserInfoContrlloer.class);
	//
	// @RequestMapping(value = { "/api/userinfo" }, method = {
	// RequestMethod.GET, RequestMethod.POST })
	// public void login(HttpServletRequest request, HttpServletResponse
	// response, Model model,UserInfoForm loginInfo) {
	//
	// LoggerUtil.info(logger, "userinfo start");
	// ResultInfo result = new ResultInfo();
	// UserInfoDO
	// userInfo=userInfoService.getUserInfo(loginInfo.getUsername(),loginInfo.getPassword());
	// if(userInfo!=null){
	// request.getSession().setAttribute("userInfo", userInfo);
	// result.setCode(200);
	// result.setSuccess(true);
	// result.setData(userInfo.getUsername());
	//
	// }else{
	// result.setCode(200);
	// result.setSuccess(false);
	//
	// result.setMessage("error");
	//
	// }
	//
	// this.writeResponseJson(response, result);
	//
	// }
	//
	// @RequestMapping(value = { "/api/logout" }, method = {
	// RequestMethod.GET,RequestMethod.POST })
	// public String logout(HttpServletRequest request,HttpServletResponse
	// response, Model model) {
	//
	//
	// LoggerUtil.info(logger, "login start");
	// request.getSession().setAttribute("userInfo", null);
	// ResultInfo result = new ResultInfo();
	// result.setCode(200);
	// result.setSuccess(true);
	// return "index";
	//
	// }
	//
	// @RequestMapping(value = { "/api/getCurrentUser" }, method = {
	// RequestMethod.GET, RequestMethod.POST })
	// public void getCurrentUser(HttpServletRequest request,
	// HttpServletResponse response, Model model) {
	//
	// ResultInfo result = new ResultInfo();
	//
	// HttpSession session=request.getSession();
	//
	// UserInfoDO userInfo= (UserInfoDO) session.getAttribute("userInfo");
	// if(userInfo!=null){
	// result.setCode(200);
	// result.setSuccess(true);
	// result.setData(userInfo.getUsername());
	// }else{
	// result.setCode(200);
	// result.setSuccess(false);
	//
	// result.setMessage("error");
	// }
	//
	//
	// this.writeResponseJson(response, result);
	// }
	//
	// @RequestMapping(value = { "/" }, method = {
	// RequestMethod.GET,RequestMethod.POST })
	// public String index(HttpServletRequest request, HttpServletResponse
	// response, Model model) {
	//
	// return "index";
	// }
	//
	// /**
	// * @return the userInfoService
	// */
	// public UserInfoService getUserInfoService() {
	// return userInfoService;
	// }
	//
	// /**
	// * @param userInfoService the userInfoService to set
	// */
	// public void setUserInfoService(UserInfoService userInfoService) {
	// this.userInfoService = userInfoService;
	// }

}
