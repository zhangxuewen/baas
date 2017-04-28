/**
 * 
 */
package com.baashome.web.base;

/**
 * @author zhangxuewen
 *
 */
public class ResultInfo {
  private boolean success;
  private int code;
  private int total;
  private Object data;
  private String message;
/**
 * @return the success
 */
public boolean getSuccess() {
	return success;
}
/**
 * @param success the success to set
 */
public void setSuccess(boolean success) {
	this.success = success;
}
/**
 * @return the code
 */
public int getCode() {
	return code;
}
/**
 * @param code the code to set
 */
public void setCode(int code) {
	this.code = code;
}
/**
 * @return the total
 */
public int getTotal() {
	return total;
}
/**
 * @param total the total to set
 */
public void setTotal(int total) {
	this.total = total;
}
/**
 * @return the data
 */
public Object getData() {
	return data;
}
/**
 * @param data the data to set
 */
public void setData(Object data) {
	this.data = data;
}
/**
 * @return the message
 */
public String getMessage() {
	return message;
}
/**
 * @param message the message to set
 */
public void setMessage(String message) {
	this.message = message;
}
  
}
