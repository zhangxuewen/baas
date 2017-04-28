package com.baashome.util.httpService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baashome.util.httpService.enums.ResultEnum;
import com.baashome.util.httpService.vo.ResultBean;

public class HttpUtil {
	public static String API_SERVER_HOST = "http://localhost:8080/fitapi"; // api提交请求


	/**
	 * @param @param
	 *            map 用于存储parm参数
	 * @param @param
	 *            url 只填写路径地址
	 * @param @return
	 *            设定文件
	 * @return Parameter 返回类型
	 * @throws @Title:
	 *             baseRequestGet
	 * @Description: 用于请求API服务，获得返回参数
	 * @author Jani
	 * @date 2016年1月19日 下午7:59:37
	 */
	public static ResultBean baseRequestGet(String path, Map<String, Object> map) {

		if (API_SERVER_HOST.isEmpty() || API_SERVER_HOST.equals("") || path.isEmpty() || path.equals("")) {
			return null;
		}
		StringBuilder url = new StringBuilder();

		if (!API_SERVER_HOST.endsWith("/")) {

			url.append(API_SERVER_HOST).append("/");
		} else {
			url.append(API_SERVER_HOST);
		}

		if (path.startsWith("/")) {
			url.append(path.substring(1, path.length())).append("?");
		} else {
			url.append(path).append("?");
		}

		Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();

			if (entry.getKey().isEmpty() || entry.getKey().equals("")) {
				continue;
			}
			try {
				url.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"))
						.append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}
		if (url.length() > 0) {
			url.deleteCharAt(url.length() - 1);
		}
		String response = HttpClientUtil.GetUrlContent(url.toString(), "utf-8");
		System.out.println("请求地址：" + url);
		System.out.println("响应结果" + response);
		ResultBean operateResult = JSONObject.parseObject(response, ResultBean.class);
		if (operateResult.getStatus() == ResultEnum.SUCCESS.getIndex()) {
			return operateResult;
		} else {
			return null;

		}
	}
}
