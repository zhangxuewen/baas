
package com.baas.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
 * 
 * @author xuewen.zhangxuewen
 *
 */
public abstract class ToString implements Serializable {

    private static final long               serialVersionUID = -8633667458324570863L;

    /** ���˴�ӡfield�ֶ� */
    private static final Collection<String> fieldNames       = new HashSet<String>();

    /**
     * �����ֶ����
     * 
     * @param o
     * @return
     */
    public static String toString(Object o) {
        if (fieldNames.isEmpty()) {
            return ToStringBuilder.reflectionToString(o, ToStringStyle.SHORT_PREFIX_STYLE);
        } else {
            return new ReflectionToStringBuilder(o, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames(fieldNames.toArray(new String[fieldNames.size()])).toString();
        }
    }

    /**
     * ��ӹ����ֶ�
     * 
     * @param fieldName
     */
    public static void addFilterField(String fieldName) {
        fieldNames.add(fieldName);
    }
    
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToString.toString(this);
    }
    
}
