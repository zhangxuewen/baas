
package com.baas.dal.utils;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;


/**
 * TODO �����ҳ������
 * @author zhongcai.szc
 *
 */
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class })})
public class PageInterceptor implements Interceptor {
    private static final ObjectFactory        DEFAULT_OBJECT_FACTORY         = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    
    private static String                     defaultDialect                 = "mysql";                                 // ��ݿ�����(Ĭ��Ϊmysql)
    private static String                     defaultPageSqlId               = ".*Page$";                               // ��Ҫ���ص�ID(����ƥ��)
    private static String                     dialect                        = "";                                      // ��ݿ�����(Ĭ��Ϊmysql)
    private static String                     pageSqlId                      = ""; 
    /* (non-Javadoc)
     * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if(invocation.getTarget() instanceof StatementHandler){
            proceesStatementHandler(invocation);
            // ��ִ��Ȩ������һ��������
            return invocation.proceed();
        }
        return null;
    }

    /**
     * @param invocation
     */
    private void proceesStatementHandler(Invocation invocation) {
        //��ȡtarget
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        //����metaObject���󣬸ö����Ŀ����ͨ�����װһ���������Ի�ȡ�����øö����ԭ�����ɷ��ʵ����ԣ�������Щ˽�����ԣ�
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
        
        // ������������(����Ŀ������ܱ�������������أ��Ӷ��γɶ�δ��?ͨ�����������ѭ�����Է������ԭʼ�ĵ�Ŀ����)
        while (metaStatementHandler.hasGetter("h")) {
            Object object = metaStatementHandler.getValue("h");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
        }
        // �������һ����������Ŀ����
        while (metaStatementHandler.hasGetter("target")) {
            Object object = metaStatementHandler.getValue("target");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
        }
        Configuration configuration = (Configuration)metaStatementHandler.getValue("delegate.configuration");
        setConfiguration(configuration);
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
        //ֻ��д��Ҫ��ҳ��sql��䡣ͨ��MappedStatement��IDƥ�䣬Ĭ����д��Page��β��MappedStatement��sql
        if(mappedStatement.getId().matches(pageSqlId)){
            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
            Object parameterObject = boundSql.getParameterObject();
            if (parameterObject == null) {
                throw new NullPointerException("parameterObject is null!");
            } else {
                Paginator<?> page = (Paginator<?>) parameterObject;
                String sql = boundSql.getSql();
                // ��дsql
                String pageSql = buildPageSql(sql, page);
                metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
                // ���������ҳ�󣬾Ͳ���Ҫmybatis���ڴ��ҳ�ˣ����������������������
                metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
                metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
                //Connection connection = (Connection) invocation.getArgs()[0];
                // �����ҳ���������ҳ��ȣ�Ŀǰopen-lib��֧��Ƕ���Ӳ�ѯ�����ע�͵����ɸ�ģ���Լ�ʵ��
                // setPageParameter(sql, connection, mappedStatement, boundSql, page);
            } 
            
        }
        
        
        
    }

    /**
     * @param sql
     * @param page
     * @return
     */
    private String buildPageSql(String sql, Paginator<?> page) {
        if (page != null) {
            StringBuilder pageSql = new StringBuilder();
            if ("mysql".equals(dialect)) {
                pageSql = buildPageSqlForMysql(sql, page);
            } else {
                return sql;
            }
            return pageSql.toString();
        } else {
            return sql;
        }
    }

    /**
     * @param sql
     * @param page
     * @return
     */
    private StringBuilder buildPageSqlForMysql(String sql, Paginator<?> page) {
        StringBuilder pageSql = new StringBuilder(100);
        pageSql.append(sql);
        pageSql.append(" limit " + page.getStart() + "," + page.getLimit());
        return pageSql;
    }

    /**
     * @param configuration
     */
    private void setConfiguration(Configuration configuration) {
        Properties properties = configuration.getVariables();
        if (properties == null) {
            dialect = defaultDialect;
            pageSqlId = defaultPageSqlId;
        } else {
            dialect = properties.getProperty("dialect");
            if (null == dialect || "".equals(dialect)) {
                dialect = defaultDialect;
            }

            pageSqlId = configuration.getVariables().getProperty("pageSqlId");
            if (null == pageSqlId || "".equals(pageSqlId)) {
                pageSqlId = defaultPageSqlId;
            }
        }
        
    }

    /* (non-Javadoc)
     * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
     */
    @Override
    public Object plugin(Object target) {
        // ��Ŀ������StatementHandler����ʱ���Ű�װĿ���࣬����ֱ�ӷ���Ŀ�걾��,����Ŀ�걻����Ĵ���
        if (target instanceof StatementHandler || target instanceof ResultSetHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    /* (non-Javadoc)
     * @see org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
     */
    @Override
    public void setProperties(Properties properties) {
        // TODO Auto-generated method stub

    }

}
