
package com.baas.dal.utils;

import java.io.Serializable;

/**
 * 
 * TODO ��ҳ��
 * @author zhongcai.szc
 *
 * @param <T>
 */
public class Paginator<T> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2653366823651608858L;
    /**
     * Ĭ��ÿҳ��¼����
     */
    private static final int  DEFAULT_LIMIT    = 10;
    /**
     * Ĭ�Ͽ�ʼ��¼λ��
     */
    private static final int  DEFAULT_START    = 0;
    /**
     * ÿҳ��¼����
     */
    private int               limit;
    /**
     * ��ʼ��¼λ��
     */
    private int               start;
    /**
     * ����
     */
    private T                 parameter;

    /**
     * �ò����ɷ�ҳ����������
     */
    private int               count;

    public Paginator(){
        this(DEFAULT_START, DEFAULT_LIMIT, null);
    }

    public Paginator(int start, int limit, T parameter){
        this.start = start;
        this.limit = limit;
        this.parameter = parameter;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return
     */
    public int getLimit() {
        if (limit <= 0) {
            return DEFAULT_LIMIT;
        }
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * @return
     */
    public int getStart() {
        if (start <= 0) {
            return DEFAULT_START;
        }
        return start;
    }

    /**
     * 
     * 
     * @param start
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * 
     * 
     * @return
     */
    public int getOffset() {
        return (getStart() - 1) * getLimit();
    }

    /**
     * 
     * 
     * @return
     */
    public T getParameter() {
        return parameter;
    }

    /**
     * 
     * 
     * @param parameter
     */
    public void setParameter(T parameter) {
        this.parameter = parameter;
    }
}
