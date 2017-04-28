package com.baas.biz.userinfo.impl;

import java.util.ArrayList;
import static org.mockito.Matchers.anyBoolean;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import org.junit.Test;

import com.baas.biz.userinfo.impl.UserInfoServiceImpl;
import com.baas.dal.dao.UserInfoDAO;

import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.anyInt;
import org.junit.Before;
import org.junit.After;
import static org.mockito.Mockito.*;
import org.mockito.Mock;

/**
 * Unit test case generate by aunit
 * 
 * @author fenglei.fl
 * @version $Id: UserInfoServiceImplAUnitTest.java, v 0.1 Tue Oct 04 17:45:41 CST 2016 fenglei.fl Exp $
 */
public class UserInfoServiceImplAUnitTest {

	private UserInfoServiceImpl userInfoServiceImpl;

	@Mock	
	private UserInfoDAO userInfoDAO;


	@Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
		userInfoServiceImpl = new UserInfoServiceImpl();
        userInfoServiceImpl.setUserInfoDAO(userInfoDAO);
    }
	
	@After
    public void tearDown() throws Exception {
    }
	
	@Test
    public void test_GetUserList() {

					    List<UserInfoDAO> list = new ArrayList<UserInfoDAO>();
		when(userInfoDAO.getUserinfoList()).thenReturn(list);
				
		    userInfoServiceImpl.getUserList();

	}
	

}