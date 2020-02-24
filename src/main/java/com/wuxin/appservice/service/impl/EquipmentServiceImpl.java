package com.wuxin.appservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuxin.appservice.dao.EquipmentMapper;
import com.wuxin.appservice.model.Equipment;
import com.wuxin.appservice.service.EquipmentService;
import com.wuxin.appservice.vo.enums.EquipmentVo;
@Service("EquipmentService")
public class EquipmentServiceImpl implements EquipmentService{
	@Autowired
	EquipmentMapper equipmentMapper;
	/**
	 * 查询设备列表
	 * @param e
	 * @return
	 */
	@Override
	public List<EquipmentVo> list(EquipmentVo e) {
		// TODO Auto-generated method stub
		return equipmentMapper.list(e);
	}
	  /**
	   * 查询设备总条数 
	   * @param e
	   * @return
	   */
	@Override
	public int count(EquipmentVo e) {
		// TODO Auto-generated method stub
		return equipmentMapper.count(e);
	}
	 /**
     * 设备添加
     * @param record
     * @return
     */
	@Override
	public int insert(Equipment record) {
		// TODO Auto-generated method stub
		return equipmentMapper.insertSelective(record);
	}
	 /**
     * 设备信息
     * @param eid
     * @return
     */
	@Override
	public EquipmentVo get(Integer eid) {
		// TODO Auto-generated method stub
		return equipmentMapper.selectByPrimaryKey(eid);
	}
	  /**
     * 设备修改
     * @param record
     * @return
     */
	@Override
	public int update(Equipment record) {
		// TODO Auto-generated method stub
		return equipmentMapper.updateByPrimaryKeySelective(record);
	}
	/**
	 * 通过员工id查询该员工所管理的设备 
	 * @param pid
	 * @return
	 */
	@Override
	public  List<EquipmentVo> selectEquipment(Equipment e) {
		// TODO Auto-generated method stub
		return equipmentMapper.selectEquipment(e);
	}
	  /**
     * 统计系统设备总数，禁用设备占总设备的比例。
     * @param e
     * @return
     */
	@Override
	public   List<EquipmentVo> forbiddenDevic(Equipment e) {
		// TODO Auto-generated method stub
		return equipmentMapper.forbiddenDevic(e);
	}
	
	  /**
     *统计当前时间点和过去时间段内部门在线人数。
     * @param eo
     * @return
     */
	@Override
	public List<EquipmentVo> onLineDevice(EquipmentVo eo) {
		// TODO Auto-generated method stub
		return equipmentMapper.onLineDevice(eo);
	}
	
	
	  /**
     *每个小时设备在线数
     * @param eo
     * @return
     */
	@Override
	public List<EquipmentVo> onLineDeviceTime(EquipmentVo eo) {
		// TODO Auto-generated method stub
		return equipmentMapper.onLineDeviceTime(eo);
	}
	/**
	 * 查询绑定设备
	 */
	@Override
	public EquipmentVo binDingEquipment(EquipmentVo uv) {
		// TODO Auto-generated method stub
		return equipmentMapper.binDingEquipment(uv);
	}
	/**
	 * 绑定设备
	 */
	@Override
	public int binDing(EquipmentVo uv) {
		// TODO Auto-generated method stub
		return equipmentMapper.binDing(uv);
	}
	/**
	 * 通过设备id查询该设备
	 */
	@Override
	public EquipmentVo selectByPrimaryKey(Integer eid) {
		// TODO Auto-generated method stub
		return equipmentMapper.selectByPrimaryKey(eid);
	}
	 /**
     * 当天设备在线总数
     * @param eo
     * @return
     */
	@Override
	public int sumCount(EquipmentVo eo) {
		// TODO Auto-generated method stub
		return equipmentMapper.sumCount(eo);
	}
	 /**
     *总台数
     * @return
     */
	@Override
	public int sum() {
		// TODO Auto-generated method stub
		return equipmentMapper.sum();
	}
	@Override
	public int insertSelective(Equipment record) {
		// TODO Auto-generated method stub
		return equipmentMapper.insertSelective(record);
	}
	
	@Override
	public Equipment checkValiableEquipment(Equipment e) {
		// TODO Auto-generated method stub
		return equipmentMapper.checkValiableEquipment(e);
	}
	
	
}
