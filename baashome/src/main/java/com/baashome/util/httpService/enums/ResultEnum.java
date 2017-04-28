package com.baashome.util.httpService.enums;

/**
 * 
 * @ClassName: ResultEnum
 * @Description: 返回信息枚举
 * @author Jani
 * @date 2016年3月31日 上午7:43:03
 */
public enum ResultEnum {

	/* 公共模块 (500以下) */
	SUCCESS(200, "ok"), // 成功
	SYS_ERROR(500, "Internal Server Error"), // 服务器异常
	SYS_FORBIDDEN(400, "Parameters of the abnormal"), // 参数异常
	
	/* 短信模块 (500-599) */
	SMS_ERROR(601, "触发业务流控限制"), // 触发业务流控限制

	/* 用户模块 (1000-1999)*/
	USER_MOBILE_ERROR(1000,"手机号格式错误"),
	USER_LOGIN_CODE_ERROR(1001,"验证码错误"),
	USER_NOT_LOGIN(1002,"用户未登录");
	
	/* 产品模块 (2000-2999)*/
	
	/* 技师模块 (3000-3999)*/
	
	/* 管理模块  (4000-4999)*/

	

	private int index;

	private String msg;

	private ResultEnum(int index, String msg) {
		this.index = index;
		this.msg = msg;
	}

	public int getIndex() {
		return index;
	}

	public String getMsg() {
		return msg;
	}
	
}
