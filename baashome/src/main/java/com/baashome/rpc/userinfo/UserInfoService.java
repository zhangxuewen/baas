/**
 * 
 */
package com.baashome.rpc.userinfo;

import com.baashome.rpc.userinfo.model.UserInfoDO;

/**
 * @author zhangxuewen
 *
 */
public interface UserInfoService {
	public UserInfoDO getUserInfo(String username,String password);

}
