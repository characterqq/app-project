package com.wuxin.appservice.service;

import com.wuxin.appservice.model.Heartbeat;

public interface HeartbeatService {
	//添加心跳表
	 int insertSelective(Heartbeat record);
}
