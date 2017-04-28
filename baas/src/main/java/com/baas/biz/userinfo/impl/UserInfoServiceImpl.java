package com.baas.biz.userinfo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.baas.biz.userinfo.UserInfoService;
import com.baas.dal.dao.UserInfoDAO;
import com.baas.dal.dataobject.UserInfoDO;
@Service
public class UserInfoServiceImpl implements UserInfoService{
    
	@Autowired
    @Qualifier("mybatisUserInfoDAO")
    private UserInfoDAO userInfoDAO;
	@Override
	public List<UserInfoDAO> getUserList() {
		// TODO Auto-generated method stub
		return userInfoDAO.getUserinfoList();
	}
	@Override
	public UserInfoDO getUserInfo(String username, String password) {
		// TODO Auto-generated method stub
		return userInfoDAO.getUserInfo( username,  password);
	}
	/**
	 * @return the userInfoDAO
	 */
	public UserInfoDAO getUserInfoDAO() {
		return userInfoDAO;
	}
	/**
	 * @param userInfoDAO the userInfoDAO to set
	 */
	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}
	

}
