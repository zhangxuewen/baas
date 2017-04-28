/**
 * 
 */
package com.wxbaas.rpc.userinfo;

import com.wxbaas.rpc.userinfo.model.UserInfoDO;

/**
 * @author zhangxuewen
 *
 */
public interface UserInfoService {
	public UserInfoDO getUserInfo(String username,String password);

}
