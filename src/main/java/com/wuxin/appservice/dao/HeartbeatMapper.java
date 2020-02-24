package com.wuxin.appservice.dao;

import com.wuxin.appservice.model.Heartbeat;

public interface HeartbeatMapper {
    int deleteByPrimaryKey(Integer hid);

    int insert(Heartbeat record);

    int insertSelective(Heartbeat record);

    Heartbeat selectByPrimaryKey(Integer hid);

    int updateByPrimaryKeySelective(Heartbeat record);

    int updateByPrimaryKey(Heartbeat record);
}