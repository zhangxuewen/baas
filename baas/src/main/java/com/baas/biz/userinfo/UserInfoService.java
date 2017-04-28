/**
 * 
 */
package com.baas.biz.userinfo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baas.dal.dao.UserInfoDAO;
import com.baas.dal.dataobject.UserInfoDO;

/**
 * @author zhangxuewen
 *
 */
@Service
public interface UserInfoService {
  public List<UserInfoDAO> getUserList();
  public UserInfoDO getUserInfo(String username,String password);
 
}
