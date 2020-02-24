package com.wuxin.appservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuxin.appservice.dao.OperationInfoMapper;
import com.wuxin.appservice.model.OperationInfo;
import com.wuxin.appservice.service.OperationInfoService;
import com.wuxin.appservice.vo.enums.LogVo;

/**
 * 操作详情实现层
 * @author hk
 *
 */
@Service("OperationInfoService")
public class OperationInfoServiceImpl implements OperationInfoService{
	@Autowired
	private OperationInfoMapper operationInfoMapper;
	@Override
	public int insertSelective(OperationInfo record) {
		// TODO Auto-generated method stub
		return operationInfoMapper.insertSelective(record);
	}
	@Override
	public List<LogVo> selsectAllLog(LogVo lv) {
		// TODO Auto-generated method stub
		return operationInfoMapper.selsectAllLog(lv);
	}
	@Override
	public int countAll(LogVo lv) {
		// TODO Auto-generated method stub
		return operationInfoMapper.countAll(lv);
	}
	

}
