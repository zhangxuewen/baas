/**
 * Function: 
 * 
 * File Created at 2016�?�?1�? * $Id$
 * 
 * Copyright 2009 Alibaba.com Corporation Limited.
 * All rights reserved.
 */
package com.baas.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO ����sqlSessionTemplate
 * @author xuewen.zhangxuewen
 *
 */
@Configuration
public class MyBatisConfig {
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    
  @Bean(name = "sqlSessionTemplate")
  public SqlSessionTemplate sqlSessionTemplate() throws Exception {
      return new SqlSessionTemplate(sqlSessionFactory);
  }
    

}
