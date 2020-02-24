package com.wuxin.appservice.util;

import com.wuxin.appservice.model.User;

import redis.clients.jedis.Jedis;

/**
 * Explain:测试RedisUtil
 * @author hk
 *
 */
public class RedisJava {
	 public static void main(String[] args) {
	     RedisUtil.setString("login", "123",1);
	     System.out.println(RedisUtil.getString("login"));
	     User user=new User();
	     user.setUserName("123");
	     RedisUtil.setModel("user", user,1);
	     System.out.println("00");
	    System.out.println(((User)RedisUtil.getModel("user")).getUserName()) ;
	   
	    }
	 }

