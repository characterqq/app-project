package com.wuxin.appservice.vo.enums;

public class IntefaceReturnBO {
   private String returnJson;    //返回给对接方的json字符串
   private String sign;         //md5签名
public String getReturnJson() {
	return returnJson;
}
public void setReturnJson(String returnJson) {
	this.returnJson = returnJson;
}
public String getSign() {
	return sign;
}
public void setSign(String sign) {
	this.sign = sign;
}
   
}
