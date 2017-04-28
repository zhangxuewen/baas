package com.baasmng.rpc;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.baasmng.util.GsonUtils;
import com.baasmng.util.ResourceUtils;



/**
 * 
 * @author zhangxuewen
 *
 */
public class BaseController {
	protected static final Logger logger = LoggerFactory.getLogger("BAAS-WEB-LOG");



	/**
	 * init
	 * 
	 * @param modelMap
	 * @param request
	 */
	@ModelAttribute
	public void init(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {

		

	}

	/**
	 * tojson
	 * 
	 * @param response
	 * @param result
	 */
	public void writeResponseJson(HttpServletResponse response, Object result) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			os.write(GsonUtils.toJson(result).getBytes("UTF-8"));
			os.flush();
		} catch (Exception ex) {
			logger.error("--->writeResponseJson error!", ex);
		} finally {
			ResourceUtils.closeQuietly(os);
		}
	}

	
}
