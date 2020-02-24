package com.wuxin.appservice.util;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

public class ReturnResultUtil implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//用来判断
	private Integer ret;
	//返回消息
	private String msg;
	//数据
	private Object data;
	
	private String sign;
	
	public ReturnResultUtil() {
		super();
	}


	public ReturnResultUtil(Integer ret, String msg, Object data) {
		super();
		this.ret = ret;
		this.msg = msg;
		this.data = data;
	}
	
	public ReturnResultUtil(Integer ret) {
		super();
		this.ret = ret;
	}
	
	public ReturnResultUtil(Integer ret, String msg) {
		super();
		this.ret = ret;
		this.msg = msg;
	}
	

	public Integer getRet() {
		return ret;
	}


	public void setRet(Integer ret) {
		this.ret = ret;
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


	private Map getMap(Map map){
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			if(entry.getValue() == null){
				map.put(entry.getKey(),"");
			}
		}
		return map;
	}


	public String getSign() {
		return sign;
	}


	public void setSign(String sign) {
		this.sign = sign;
	}

	
}
