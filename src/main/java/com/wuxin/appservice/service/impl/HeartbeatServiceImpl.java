package com.wuxin.appservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuxin.appservice.dao.HeartbeatMapper;
import com.wuxin.appservice.model.Heartbeat;
import com.wuxin.appservice.service.HeartbeatService;
@Service("HeartbeatService")
public class HeartbeatServiceImpl implements HeartbeatService{
	@Autowired
	HeartbeatMapper heartbeatMapper;
	@Override
	public int insertSelective(Heartbeat record) {
		// TODO Auto-generated method stub
		return heartbeatMapper.insertSelective(record);
	}

}
