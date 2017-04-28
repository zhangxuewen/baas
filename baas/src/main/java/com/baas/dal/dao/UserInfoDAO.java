/**
 * 
 */
package com.baas.dal.dao;

import java.util.List;

import com.baas.dal.dataobject.UserInfoDO;

/**
 * @author zhangxuewen
 *
 */
public interface UserInfoDAO {
	/**
	 * 
	 * @return
	 */
   public List<UserInfoDAO> getUserinfoList();
    /**
     * 
     * @param username
     * @param password
     * @return
     */
	public UserInfoDO getUserInfo(String username, String password);
}
