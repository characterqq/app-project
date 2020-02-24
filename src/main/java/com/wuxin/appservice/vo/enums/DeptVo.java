package com.wuxin.appservice.vo.enums;

import java.io.Serializable;
import java.util.List;


import com.wuxin.appservice.model.Dept;
import com.wuxin.appservice.model.User;

/**
 * 部门信息
 * @author hk
 *
 */
public class DeptVo extends Dept implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer uid;
	private String userName;
	
	private List<DeptVo> deptVolist;
	
	private List<User> userList;
	public List<DeptVo> getDeptVolist() {
		return deptVolist;
	}
	public void setDeptVolist(List<DeptVo> deptVolist) {
		this.deptVolist = deptVolist;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
