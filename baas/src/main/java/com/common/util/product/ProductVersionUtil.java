
package com.common.util.product;

import com.baas.util.StringUtil;

/**
 * 产品version的工具类
 * 
 * @author jingmeng.wu
 * @version $Id: ProductIDUtil.java, v 0.1 2014-1-8 下午09:53:40 jingmeng.wu Exp $
 */
public class ProductVersionUtil {

    /**
     * 获取版本前三位
     * 
     */
    public static String getThreeProductVersion(String productVersion) {

        return StringUtil.substringBeforeLast(productVersion, ".");
    }

}
