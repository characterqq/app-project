package com.wuxin.appservice.vo.enums;

import java.io.Serializable;
import java.util.List;

import com.wuxin.appservice.model.Logindata;
import com.wuxin.appservice.model.OperationInfo;


public class LogVo extends OperationInfo implements Serializable{
	//操作详情
	private List<LogVo> logVolist;
	//管理员名称
	private String userName;
	//员工名称
	private String name;
	//员工警号
	private String policeNo;
	//
	private String createDate;
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public List<LogVo> getLogVolist() {
		return logVolist;
	}
	public void setLogVolist(List<LogVo> logVolist) {
		this.logVolist = logVolist;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPoliceNo() {
		return policeNo;
	}
	public void setPoliceNo(String policeNo) {
		this.policeNo = policeNo;
	}
	
}
