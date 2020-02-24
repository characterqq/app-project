package com.wuxin.appservice.dao;

import java.util.List;

import com.wuxin.appservice.model.Equipment;
import com.wuxin.appservice.vo.enums.EquipmentVo;
import com.wuxin.appservice.vo.enums.UserVo;

public interface EquipmentMapper {
	//查询绑定设备
	EquipmentVo binDingEquipment(EquipmentVo uv);
	//绑定设备
	int binDing(EquipmentVo uv);
	/**
	 * 通过员工id查询该员工所管理的设备 
	 * @param pid
	 * @return
	 */
	 List<EquipmentVo> selectEquipment(Equipment e);
	
    int deleteByPrimaryKey(Integer eid);

    int insert(Equipment record);

    int insertSelective(Equipment record);

    EquipmentVo selectByPrimaryKey(Integer eid);

    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);
    
    List<EquipmentVo> list(EquipmentVo e);
    
    int count(EquipmentVo e);
   
    List<EquipmentVo> forbiddenDevic(Equipment e);
    
    List<EquipmentVo> onLineDevice(EquipmentVo eo);
    
    
    List<EquipmentVo> onLineDeviceTime(EquipmentVo eo);
    
    
    int sumCount(EquipmentVo eo);
    
    
    int sum();
    
    /**
     * 根据警员id检查其名下所有可用的设备 
     * @param e
     * @return
     */
    Equipment checkValiableEquipment(Equipment e);
}