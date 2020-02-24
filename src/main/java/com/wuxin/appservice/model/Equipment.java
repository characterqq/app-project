package com.wuxin.appservice.model;

import java.io.Serializable;

import com.wuxin.appservice.util.PageUtil;



/**
 * 设备信息实体类
 * @author hn
 *
 */
public class Equipment extends PageUtil implements Serializable{
	//设备id
    private Integer eid;
    //所属人员编号，外键链接员工表（pid
    private Integer pid;
    //员工状态：0、在线 1、离线 2、禁用 3损坏
    private Integer status;
    //设备信息最近修改的时间
    private String updateDate;
    //设备的添加时间
    private String createDate;
    //设备备注
    private String remark;
    //设备名称
    private String equipmentName;
    //设备系统
    private String equipmentSys;
    //设备类型
    private String equipmentType;
    
    private String equipmentNo;
    
    private String number;
  
    private Integer uid;
    
    private Integer createBy;
    
    private Integer updateBy;
    
    private Integer equipmentStatus;
    
	public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName == null ? null : equipmentName.trim();
    }

    public String getEquipmentSys() {
        return equipmentSys;
    }

    public void setEquipmentSys(String equipmentSys) {
        this.equipmentSys = equipmentSys == null ? null : equipmentSys.trim();
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType == null ? null : equipmentType.trim();
    }

	public String getEquipmentNo() {
		return equipmentNo;
	}

	public void setEquipmentNo(String equipmentNo) {
		this.equipmentNo = equipmentNo;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
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

	public Integer getEquipmentStatus() {
		return equipmentStatus;
	}

	public void setEquipmentStatus(Integer equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}
    
    
}