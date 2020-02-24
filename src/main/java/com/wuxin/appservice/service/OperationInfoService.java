package com.wuxin.appservice.service;

import java.util.List;

import com.wuxin.appservice.model.OperationInfo;
import com.wuxin.appservice.vo.enums.LogVo;
/**
 * 操作详情
	service层
 * @author hk
 *
 */
public interface OperationInfoService {
	
	int countAll(LogVo lv);
	
	List<LogVo> selsectAllLog(LogVo lv);
	
	int insertSelective(OperationInfo record);
}
