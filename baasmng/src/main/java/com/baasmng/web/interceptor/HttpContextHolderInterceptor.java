
package com.baasmng.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.baasmng.util.LoggerUtil;
import com.baasmng.util.UserAgentUtil;





/**
 * HttpContextHolderInterceptor 
 * 
 * @author xuewen.zhangxuewen
 * @version $Id: HttpContextHolderInterceptor.java,
 *      
 */
public class HttpContextHolderInterceptor extends HandlerInterceptorAdapter {

	public static final String LOGON_ID = "logonId";

	private final static Logger logger = LoggerFactory.getLogger(HttpContextHolderInterceptor.class); 

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LoggerUtil.info(logger, "preHandle, params=", request.getParameterMap());

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//
		super.postHandle(request, response, handler, modelAndView);
		//LoggerUtil.info(LOGGER, "preHandle, params=", request.getParameterMap());


		if (modelAndView != null && StringUtils.isNotBlank(modelAndView.getViewName())) {

			UserAgentUtil uAgentInfo = new UserAgentUtil(request);
			if (uAgentInfo.detectMobileQuick()) {
				String vm = StringUtils.startsWith(modelAndView.getViewName(), "/")? modelAndView.getViewName().substring(1) : modelAndView.getViewName();
				modelAndView.setViewName(vm+"_mobile");
			}

		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		try {
//			LoggerUtil.info(LOGGER, "preHandle, params=", request.getParameterMap());
//		} catch (Exception e) {
//			LoggerUtil.error(LOGGER, e, "preHandle, params=", request.getParameterMap());
//		}
		super.afterCompletion(request, response, handler, ex);
	}

}
