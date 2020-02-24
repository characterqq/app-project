package com.wuxin.appservice.vo.enums;

import java.io.Serializable;
import java.util.List;

import com.wuxin.appservice.model.Logindata;

public class LoginVo extends Logindata implements Serializable{
	
	//登录操作
	private List<LoginVo>  loginVoList;
	//设备名称
	private String equipmentName;
	//设备编号
	private Integer eid;
	//所属角色
	private String roleName;
	//登录人名称
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public List<LoginVo> getLoginVoList() {
		return loginVoList;
	}
	public void setLoginVoList(List<LoginVo> loginVoList) {
		this.loginVoList = loginVoList;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
