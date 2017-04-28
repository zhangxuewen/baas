package com.wxbaas.util.httpService;

import java.util.Map;

import com.wxbaas.util.httpService.enums.ResultEnum;
import com.wxbaas.util.httpService.vo.ResultBean;

public class CheckParamUtil {

	/**
	 * 验证Map类型参数是否存在
	 * @param mustParam 必要参数
	 * @param resultMap 存放数据Map
	 * @return
	 */
	public static ResultBean checkMapParam(String[] mustParam, Map<String, Object> resultMap) {

		for (int i = 0; i < resultMap.size() && i < mustParam.length; i++) {
			if (!resultMap.containsKey((mustParam[i])) || resultMap.get((mustParam[i])) == null
					|| resultMap.get((mustParam[i])).toString().equals(""))
				return new ResultBean(ResultEnum.SYS_FORBIDDEN.getIndex(), ResultEnum.SYS_FORBIDDEN.getMsg(), null);
		}
		return null;
	}
}
