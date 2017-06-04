
package com.common.util.product;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.baas.util.StringUtil;

/**
 * 
 * @author jingmeng.wu
 * @version $Id: AppVersionUtil.java, v 0.1 2014-2-26 下午6:00:12 jingmeng.wu Exp
 *          $
 */
public class AppVersionUtil {

	/**
	 * 比较v1和v2的大小, 以小数点分割成四个整数，从左到右依次进行比较 eg:
	 * assertTrue(compareVersion("1.0.0.1", "2.0.0.0") <0) ,
	 * assertTrue(compareVersion("1.0.0.1", "1.0.0.1") == 0)
	 * 
	 * @see compareVersion(java.lang.String,
	 *      java.lang.String)
	 */
	public static int compareVersion(String version1, String version2) {
		String[] v1 = spitString(version1);
		String[] v2 = spitString(version2);
		if (null == v1 || null == v2) { // v1 is invalid, version1<version2
			throw new IllegalArgumentException("v1 or v2 should not be null");
		}
		if (v1.length < v2.length) {
			throw new IllegalArgumentException("v1 or v2 should not be the same length");
		}
		for (int i = 0; i < v1.length; i++) {
			int value1 = Integer.parseInt(v1[i]);
			int value2 = Integer.parseInt(v2[i]);
			if (value1 != value2) {
				return value1 - value2;
			}
		}
		return 0;
	}

	/**
	 * 比较两个版本号的大小，不在乎它们的长度(小数点的位数是否相同)
	 * 
	 * @return
	 */
	public static int compareVersionIgnoreLength(String version1, String version2) {
		String[] v1 = spitString(version1);
		String[] v2 = spitString(version2);
		if (v1.length != v2.length) {
			boolean version1Longer = v1.length > v2.length;
			StringBuilder shorter = new StringBuilder(version1Longer ? version2 : version1);
			int toAppend = Math.abs(v1.length - v2.length);
			for (int i = 0; i < toAppend; i++) {
				shorter.append(".0");
			}
			if (version1Longer) {
				version2 = shorter.toString();
			} else {
				version1 = shorter.toString();
			}
		}
		return compareVersion(version1, version2);
	}

	/**
	 * 通过“.”来分割字符串
	 * 
	 * @param version
	 * @return
	 */
	public static String[] spitString(String version) {
		if (version == null) {
			return null;
		}
		// String checkVersion = "^\\d{1,2}[.]\\d{1,2}[.]\\d{1,2}[.]\\d{1,6}$";
		// Pattern p = null; //正则表达式对象
		// Matcher m = null; //操作的字符串的对像
		// p = Pattern.compile(checkVersion);//载入验证规则
		// m = p.matcher(version);//载入需要验证的字符串
		// if (m.matches()) {//验证是否符合表达式
		return StringUtil.split(version, ".");
		// } else {
		// return null;
		// }
	}

	/**
	 * 去掉jar版本最后的时间戳
	 */
	public static String getRealVersion(String version) {
		String checkVersion = "^\\d{1,2}[.]\\d{1,2}[.]\\d{1,2}[.]\\d{1,16}[.]\\d{1,16}$";
		Pattern p = null; // 正则表达式对象
		Matcher m = null; // 操作的字符串的对像
		p = Pattern.compile(checkVersion);// 载入验证规则
		m = p.matcher(version);// 载入需要验证的字符串
		if (m.matches()) {// 验证是否符合表达式
			return StringUtil.substringBeforeLast(version, ".");
		} else {
			return version;
		}
	}

	/**
	 * 比较v1和v2的大小, 以小数点分割成四个整数，从左到右依次进行比较 eg:
	 * assertTrue(compareVersion("1.0.0.1", "2.0.0.0") <0) ,
	 * assertTrue(compareVersion("1.0.0.1", "1.0.0.1") == 0)
	 * 
	 * @see com.alipay.mobileapp.core.service.update.ClientProductUpdateService#compareVersion(java.lang.String,
	 *      java.lang.String)
	 */
	public static int compareVersionWithX(String version1, String version2) {
		String[] v1 = spitString(version1);
		String[] v2 = spitString(version2);
		if (null == v1 || null == v2) { // v1 is invalid, version1<version2
			throw new IllegalArgumentException("v1 or v2 should not be null");
		}
		if (v1.length < v2.length) {
			throw new IllegalArgumentException("v1 or v2 should not be the same length");
		}
		for (int i = 0; i < v1.length; i++) {
			if ("*".equals(v1[i])) {
				v1[i] = "99999999";
			}
			if ("*".equals(v2[i])) {
				v2[i] = "99999999";
			}

			int value1 = Integer.parseInt(v1[i]);
			int value2 = Integer.parseInt(v2[i]);
			if (value1 != value2) {
				return value1 - value2;
			}
		}
		return 0;
	}

	/**
	 * 截取版本号，只取前3段
	 * 
	 * @param productVersion
	 * @return
	 */
	public static String cutVersion(String productVersion) {
		String[] vers = spitStringAndCheck(productVersion);
		if (vers == null || vers.length < 4) {
			return productVersion;
		}
		return vers[0] + "." + vers[1] + "." + vers[2];
	}

	/**
	 * 通过“.”来分割字符串
	 * 
	 * @param version
	 * @return
	 */
	public static String[] spitStringAndCheck(String version) {
		if (version == null) {
			return null;
		}
		String checkVersion = "^\\d{1,2}[.]\\d{1,2}[.]\\d{1,2}[.]\\d{1,10}$";
		Pattern p = null; // 正则表达式对象
		Matcher m = null; // 操作的字符串的对像
		p = Pattern.compile(checkVersion);// 载入验证规则
		m = p.matcher(version);// 载入需要验证的字符串
		if (m.matches()) {// 验证是否符合表达式
			return StringUtil.split(version, ".");
		} else {
			return null;
		}
	}
}
