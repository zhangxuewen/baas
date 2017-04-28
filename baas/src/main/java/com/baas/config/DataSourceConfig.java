/**
 * Function: 
 * 
 * File Created at 2016�?�?7�? * $Id$
 * 
 * Copyright 2009 Alibaba.com Corporation Limited.
 * All rights reserved.
 */
package com.baas.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * TODO �������Դ
 * @author xuewen.zhangxuewen
 */
@Configuration
@MapperScan(basePackages = "com.iotnet")
@ComponentScan(basePackages = "com.iotnet")
public class DataSourceConfig {
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);

    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolve = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setMapperLocations(resolve.getResources("classpath:/dal/mybatis/*Mapper.xml"));
        return sqlSessionFactory.getObject();

    }


}
