package com.baasmng.util.httpService.vo;
/**
 * 操作结果
 * @author Jani
 * @date 2016年1月19日15:14:22
 */
public class ResultBean {

	private int status;
	
	private String msg;
	
	private Object data;

	public ResultBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResultBean(int status,String msg,Object data){
		this.status = status;
		this.msg = msg;
		this.data =data;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
