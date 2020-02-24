package com.wuxin.appservice.model;

import java.io.Serializable;

import com.wuxin.appservice.util.PageUtil;
/**
 * 部门信息实体类
 * @author hk
 *
 */
public class Dept extends PageUtil implements Serializable{
	//部门编号
    private Integer did;
    //部门名称
    private String dname;
    //部门状态：1、正常 2、禁用
    private Integer status;
    //管理员编号，外键（管理员表uid）
    private Integer uid;
    //部门创建时间
    private String createDate;
    //部门信息最近修改时间
    private String updateDate;
    //备注
    private String remark;
    
    private String userName;
    
    private Integer createBy;
    
    private Integer updateBy;
    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname == null ? null : dname.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
    
    
    
    
}