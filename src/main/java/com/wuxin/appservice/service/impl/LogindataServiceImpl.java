package com.wuxin.appservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuxin.appservice.dao.LogindataMapper;
import com.wuxin.appservice.model.Logindata;
import com.wuxin.appservice.service.LogindataService;
import com.wuxin.appservice.vo.enums.LoginVo;

@Service("LogindataService")
public class LogindataServiceImpl implements LogindataService{
	@Autowired
	private LogindataMapper logindataMapper;
	@Override
	public int insert(Logindata record) {
		// TODO Auto-generated method stub
		return logindataMapper.insert(record);
	}
	@Override
	public int count(LoginVo lv) {
		// TODO Auto-generated method stub
		return logindataMapper.count(lv);
	}
	@Override
	public List<LoginVo> selsectLog(LoginVo lv) {
		// TODO Auto-generated method stub
		return logindataMapper.selsectLog(lv);
	}

}
