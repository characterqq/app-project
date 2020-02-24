package com.wuxin.appservice.dao;

import com.wuxin.appservice.model.Sysauthorization;

public interface SysauthorizationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sysauthorization record);

    int insertSelective(Sysauthorization record);

    Sysauthorization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sysauthorization record);

    int updateByPrimaryKey(Sysauthorization record);
}