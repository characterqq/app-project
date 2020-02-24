package com.wuxin.appservice.dao;

import java.util.List;

import com.wuxin.appservice.model.OperationInfo;
import com.wuxin.appservice.vo.enums.LogVo;

public interface OperationInfoMapper {
	
	int countAll(LogVo lv);
	
	List<LogVo> selsectAllLog(LogVo lv);
    
    int deleteByPrimaryKey(OperationInfo oiid);

    int insert(OperationInfo record);

    int insertSelective(OperationInfo record);

    OperationInfo selectByPrimaryKey(Integer oiid);

    int updateByPrimaryKeySelective(OperationInfo record);

    int updateByPrimaryKey(OperationInfo record);
}