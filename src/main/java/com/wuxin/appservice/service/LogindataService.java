package com.wuxin.appservice.service;

import java.util.List;

import com.wuxin.appservice.model.Logindata;
import com.wuxin.appservice.vo.enums.LoginVo;

public interface LogindataService {
	//登录日志
	int count(LoginVo lv);
	//登录日志
	List<LoginVo> selsectLog(LoginVo lv);
	 
	int insert(Logindata record);
}
