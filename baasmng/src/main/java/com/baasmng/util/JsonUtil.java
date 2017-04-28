
package com.baasmng.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author xuewen.zhangxuewen
 * @version $Id: JsonUtil.java, v 0.1 2013-11-28 ����10:26:59 xuewen.zhangxuewen $
 */
public class JsonUtil {

	/** ��־ */
	// private static final Logger logger = null;

	private final static String WEB_GBK = "UTF-8";

	/**
	 * ���ص���������ݵ������ Ĭ����GBK
	 */
	public static void writeBack(HttpServletResponse res, Object ret) {
		if (ret == null) {
			return;
		}
		try {
			String r = ret.toString();
			res.setContentType("application/json; charset=" + WEB_GBK);
			res.getWriter().write(r);
		} catch (IOException e) {

			// logger.error("Json���ת������", e);
		}
	}

	/**
	 * д��JSON���� ĳЩjs�����Ҫ��ȷָ����������
	 * 
	 * @param res
	 * @param ret
	 * @param charset
	 */
	public static void writeBack(HttpServletResponse res, Object ret, String charset) {

		if (ret == null) {
			return;
		}
		try {
			String r = ret.toString();
			res.setContentType("application/json; charset=" + charset);
			res.getWriter().write(r);
		} catch (IOException e) {
			// logger.error("Json���ת������", e);
		}
	}

	/**
	 * д��JSON���� ĳЩjs�����Ҫ��ȷָ����������
	 * 
	 * @param res
	 * @param ret
	 * @param charset
	 */
	public static void writeBack(HttpServletResponse res, String ret, String charset) {

		if (ret == null) {
			return;
		}
		try {
			res.setContentType("application/json; charset=" + charset);
			res.getWriter().write(ret);
		} catch (IOException e) {
			// logger.error("Json���ת������", e);
		}
	}

	/**
	 * ������ݵ������
	 */
	public static void writeBackObjectArray(HttpServletResponse res, Object[] rets, boolean needToTransferJson) {
		if (rets == null) {
			return;
		}
		StringBuilder sb = new StringBuilder();

		String r;
		for (Object o : rets) {
			sb.append(o.toString());
		}
		r = sb.toString().replaceAll("\\}\\{", ",");

	}

	/**
	 * 
	 * 
	 * @param response
	 * @throws IOException
	 */
	public static void printMsg(HttpServletResponse response, boolean isSuccess, String msg) {
		response.setContentType("application/json; charset=GBK");// ע��������json
		PrintWriter writer = null;

	}

	/**
	 * 
	 * 
	 * @param response
	 * @throws IOException
	 */
	public static void printMsg(HttpServletResponse response, String charset, boolean isSuccess, String msg) {
		response.setContentType("application/json; charset=" + charset);// ע��������json
		PrintWriter writer = null;

	}

}
