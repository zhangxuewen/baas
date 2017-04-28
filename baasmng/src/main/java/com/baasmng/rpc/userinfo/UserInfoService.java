/**
 * 
 */
package com.baasmng.rpc.userinfo;

import com.baasmng.rpc.userinfo.model.UserInfoDO;

/**
 * @author zhangxuewen
 *
 */
public interface UserInfoService {
	public UserInfoDO getUserInfo(String username,String password);

}
