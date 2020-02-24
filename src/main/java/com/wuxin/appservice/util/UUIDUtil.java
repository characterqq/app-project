package com.wuxin.appservice.util;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {
    
	/**
	 * 生成UUID，替换掉-
	 * @return
	 */
	public static String getUUID(){
		UUID uid=UUID.randomUUID();
		String str=uid.toString();
        return str.replaceAll("-","");
	}
	
	/**
	 * 生成随机数
	 * @param 长度
	 * @return
	 */
	public static String randomStr(int length){
		Random random=new Random();
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i <length; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		getNonceStr();
	}
	
	/**
	 * 支付接口生成随机字符串
	 * @return
	 */
	 public static String getNonceStr(){
		  final String uuid = UUID.randomUUID().toString();
		  System.out.println(uuid.substring(0,30));
		  return uuid.substring(0,30);
	  }
}
