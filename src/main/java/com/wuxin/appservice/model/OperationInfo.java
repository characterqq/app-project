package com.wuxin.appservice.model;

import java.io.Serializable;

import com.wuxin.appservice.util.PageUtil;

public class OperationInfo extends PageUtil implements Serializable{
	 private Integer oiid;

	    private Integer uid;

	    private String oldvalue;

	    private String newvalue;

	    private Integer type;

	    private Integer updateId;

	    private String content;

	    private Integer createBy;

	    private Integer updateBy;

	    private String createDate;

	    private String status;

	    public Integer getOiid() {
	        return oiid;
	    }

	    public void setOiid(Integer oiid) {
	        this.oiid = oiid;
	    }

	    public Integer getUid() {
	        return uid;
	    }

	    public void setUid(Integer uid) {
	        this.uid = uid;
	    }

	    public String getOldvalue() {
	        return oldvalue;
	    }

	    public void setOldvalue(String oldvalue) {
	        this.oldvalue = oldvalue == null ? null : oldvalue.trim();
	    }

	    public String getNewvalue() {
	        return newvalue;
	    }

	    public void setNewvalue(String newvalue) {
	        this.newvalue = newvalue == null ? null : newvalue.trim();
	    }

	    public Integer getType() {
	        return type;
	    }

	    public void setType(Integer type) {
	        this.type = type;
	    }

	    public Integer getUpdateId() {
	        return updateId;
	    }

	    public void setUpdateId(Integer updateId) {
	        this.updateId = updateId;
	    }

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content == null ? null : content.trim();
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

	    public String getCreateDate() {
	        return createDate;
	    }

	    public void setCreateDate(String createDate) {
	        this.createDate = createDate == null ? null : createDate.trim();
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status == null ? null : status.trim();
	    }
    
}