/**
 * 
 */
package com.wxbaas.rpc.userinfo;

import java.util.HashMap;
import java.util.Map;

import com.wxbaas.rpc.userinfo.model.UserInfoDO;
import com.wxbaas.util.httpService.HttpUtil;
import com.wxbaas.util.httpService.enums.ResultEnum;
import com.wxbaas.util.httpService.vo.ResultBean;

/**
 * @author zhangxuewen
 *
 */
public class UserInfoServiceImpl implements UserInfoService {
	private Map<String, Object> paramMap;
	public UserInfoDO getUserInfo(String username, String password) {
		paramMap = new HashMap<String, Object>();
		paramMap.put("channel", "gzh");
		paramMap.put("genreid", username);
		paramMap.put("classify", password);
		ResultBean rBean = HttpUtil.baseRequestGet("product/list.html", paramMap);
		if (rBean == null){
			return null;
		}
		
		return null;
	};

}
