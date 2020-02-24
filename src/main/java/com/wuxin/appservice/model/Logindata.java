package com.wuxin.appservice.model;

import java.io.Serializable;

import com.wuxin.appservice.util.PageUtil;

public class Logindata extends PageUtil implements Serializable{
    private Integer ldid;

    private Integer uid;

    private Integer eid;

    private String loginIp;

    private String loginPlcae;

    private Integer rid;

    private String remark;
    
    private String loginDate;
    
	public Integer getLdid() {
		return ldid;
	}

	public void setLdid(Integer ldid) {
		this.ldid = ldid;
	}

	
	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginPlcae() {
		return loginPlcae;
	}

	public void setLoginPlcae(String loginPlcae) {
		this.loginPlcae = loginPlcae;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

  
}