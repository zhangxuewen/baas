/**
 * 
 */
package com.baas.dal.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baas.dal.dao.UserInfoDAO;
import com.baas.dal.dataobject.UserInfoDO;

/**
 * @author zhangxuewen
 *
 */
@Repository
public class MybatisUserInfoDAO implements UserInfoDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<UserInfoDAO> getUserinfoList() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("listInfo");
	}

	@Override
	public UserInfoDO getUserInfo(String username, String password) {
		Map<String,Object> param =new HashMap<String,Object>();
		param.put("username", username);
		param.put("password", password);
		return sqlSessionTemplate.selectOne("getUserinfo", param);
	}

}
