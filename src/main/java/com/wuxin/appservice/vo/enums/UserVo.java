package com.wuxin.appservice.vo.enums;

import java.io.Serializable;
import java.util.List;

import com.wuxin.appservice.model.Role;
import com.wuxin.appservice.model.User;

public class UserVo extends User implements Serializable{
private String rid;
public String getRid() {
	return rid;
}
public void setRid(String rid) {
	this.rid = rid;
}
private String roleName;

private List<UserVo> userVolist;

private List<Role> roleList ;

private User users;

private String equipmentName;
//设备id
private Integer eid;
//原密码
private String oldPassword;
//新修改管理员密码时间
private String updateDates;
public String getUpdateDates() {
	return updateDates;
}
public void setUpdateDates(String updateDates) {
	this.updateDates = updateDates;
}
public String getOldPassword() {
	return oldPassword;
}
public void setOldPassword(String oldPassword) {
	this.oldPassword = oldPassword;
}
public User getUsers() {
	return users;
}
public void setUsers(User users) {
	this.users = users;
}
public String getRoleName() {
	return roleName;
}

public void setRoleName(String roleName) {
	this.roleName = roleName;
}

public List<UserVo> getUserVolist() {
	return userVolist;
}

public void setUserVolist(List<UserVo> userVolist) {
	this.userVolist = userVolist;
}

public List<Role> getRoleList() {
	return roleList;
}

public void setRoleList(List<Role> roleList) {
	this.roleList = roleList;
}
public String getEquipmentName() {
	return equipmentName;
}
public void setEquipmentName(String equipmentName) {
	this.equipmentName = equipmentName;
}
public Integer getEid() {
	return eid;
}
public void setEid(Integer eid) {
	this.eid = eid;
}

}
