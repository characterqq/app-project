package com.wuxin.appservice.service;

import java.util.List;

import com.wuxin.appservice.model.Equipment;
import com.wuxin.appservice.vo.enums.EquipmentVo;

public interface EquipmentService {
	//通过设备id查询该设备
	EquipmentVo selectByPrimaryKey(Integer eid);
	//绑定设备
	int binDing(EquipmentVo uv);
	//查询绑定设备
	EquipmentVo binDingEquipment(EquipmentVo uv);
	
	/**
	 * 通过员工id查询该员工所管理的设备 
	 * @param pid
	 * @return
	 */
	 List<EquipmentVo> selectEquipment(Equipment e);
	
	/**
	 * 查询设备列表
	 * @param e
	 * @return
	 */
	 List<EquipmentVo> list(EquipmentVo e);
	  /**
	   * 查询设备总条数 
	   * @param e
	   * @return
	   */
	    int count(EquipmentVo e);
	    
	    /**
	     * 设备添加
	     * @param record
	     * @return
	     */
	    int insert(Equipment record);
	    
	    int insertSelective(Equipment record);
	    
	    /**
	     * 设备信息
	     * @param eid
	     * @return
	     */
	    EquipmentVo get(Integer eid);
	    /**
	     * 设备修改
	     * @param record
	     * @return
	     */
	    int update(Equipment record);
	    /**
	     * 统计系统设备总数，禁用设备占总设备的比例。
	     * @param e
	     * @return
	     */
	    List<EquipmentVo> forbiddenDevic(Equipment e);
	    
	    /**
	     *统计当前时间点和过去时间段内部门在线人数。
	     * @param eo
	     * @return
	     */
	    List<EquipmentVo> onLineDevice(EquipmentVo eo);
	    
	    
	    
	    /**
	     *每个小时设备在线数
	     * @param eo
	     * @return
	     */
	    List<EquipmentVo> onLineDeviceTime(EquipmentVo eo);
	    /**
	     * 当天设备在线总数
	     * @param eo
	     * @return
	     */
	    int sumCount(EquipmentVo eo);
	    
	    /**
	     *总台数
	     * @return
	     */
	    int sum();
	    
	    /**
	     * 根据警员id检查其名下所有可用的设备 
	     * @param e
	     * @return
	     */
	    Equipment checkValiableEquipment(Equipment e);
	    
}
