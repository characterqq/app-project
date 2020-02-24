package com.wuxin.appservice.dao;

import java.util.List;

import com.wuxin.appservice.model.Logindata;
import com.wuxin.appservice.vo.enums.LoginVo;

public interface LogindataMapper {
	int count(LoginVo lv);
	
	List<LoginVo> selsectLog(LoginVo lv);
    int deleteByPrimaryKey(Integer ldid);

    int insert(Logindata record);

    int insertSelective(Logindata record);

    Logindata selectByPrimaryKey(Integer ldid);

    int updateByPrimaryKeySelective(Logindata record);

    int updateByPrimaryKey(Logindata record);
}