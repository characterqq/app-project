package com.wuxin.appservice.vo.enums;

import java.io.Serializable;
import java.util.List;

import com.wuxin.appservice.model.Equipment;
import com.wuxin.appservice.model.Person;
import com.wuxin.appservice.model.Role;
import com.wuxin.appservice.model.User;

public class EquipmentVo extends Equipment implements Serializable{
//人员警号
private Integer policeNo;
//人员密码
private String personPwd;
//电子证书
private String publicKey;

private String name;

private String starDate;

private String endDate;

private String userName;

private String userPwd;

private Integer count;//设备总数

private Integer forbidden ;//禁用设备

private Double proportion;//设备占比

private Integer hour;//设备总数

private List<EquipmentVo> equipmentVolist;

private List<Person> peresonlist;

public String getPublicKey() {
	return publicKey;
}

public void setPublicKey(String publicKey) {
	this.publicKey = publicKey;
}

public String getPersonPwd() {
	return personPwd;
}

public void setPersonPwd(String personPwd) {
	this.personPwd = personPwd;
}

public Integer getPoliceNo() {
	return policeNo;
}

public void setPoliceNo(Integer policeNo) {
	this.policeNo = policeNo;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public List<EquipmentVo> getEquipmentVolist() {
	return equipmentVolist;
}

public void setEquipmentVolist(List<EquipmentVo> equipmentVolist) {
	this.equipmentVolist = equipmentVolist;
}

public List<Person> getPeresonlist() {
	return peresonlist;
}

public void setPeresonlist(List<Person> pList) {
	this.peresonlist = pList;
}

public String getStarDate() {
	return starDate;
}

public void setStarDate(String starDate) {
	this.starDate = starDate;
}

public String getEndDate() {
	return endDate;
}

public void setEndDate(String endDate) {
	this.endDate = endDate;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getUserPwd() {
	return userPwd;
}

public void setUserPwd(String userPwd) {
	this.userPwd = userPwd;
}

public Integer getCount() {
	return count;
}

public void setCount(Integer count) {
	this.count = count;
}

public Integer getForbidden() {
	return forbidden;
}

public void setForbidden(Integer forbidden) {
	this.forbidden = forbidden;
}

public Double getProportion() {
	return proportion;
}

public void setProportion(Double proportion) {
	this.proportion = proportion;
}

public Integer getHour() {
	return hour;
}

public void setHour(Integer hour) {
	this.hour = hour;
}


	
}
