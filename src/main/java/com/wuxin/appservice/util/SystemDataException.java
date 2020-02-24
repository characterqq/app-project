package com.wuxin.appservice.util;

/**
 * 自定义异常-系统数据异常
 * @author wuxy
 *
 */
public class SystemDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;//异常描述信息

	public  SystemDataException(){}
	
	public SystemDataException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
