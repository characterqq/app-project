package com.wuxin.appservice.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wuxin.appservice.util.PageUtil;

public class Person extends PageUtil implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 员工状态：正常
	 */
	public static final int PERSON_STATUS_NORMAL = 0;
	/**
	 * 员工状态：禁止
	 */
	public static final int PERSON_STATUS_FORBIT = 1;
	
	//员工id
    private Integer pid;
    //真实姓名
    private String name;
    //员工身份证号码
    private String idcardNo;
    //员工警号
    private Integer policeNo;
    //员工手机号码
    private String mobile;
    //电子证书
    private String publicKey;
    //部门编号，外键（部门表的did）
    private Integer deptId;
    //人员状态：0、正常 1、禁用
    private Integer status;
    //员工信息最近修改时间
    private String updateDate;
    //人员的创建时间
    private String createDate;
    //人员备注
    private String remark;
    //可注册设备数量
    private Integer isCount;
    //邮箱
    private String email;
    //部门名称
    private String dname;
	//部门id
    private Integer did;
	
  //员工密码
    private String personPwd;
    
  //设备id
    private Integer eid;
    
    private Integer createBy;
    
    private Integer updateBy;

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public Integer getPoliceNo() {
		return policeNo;
	}

	public void setPoliceNo(Integer policeNo) {
		this.policeNo = policeNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
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
		this.updateDate = updateDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsCount() {
		return isCount;
	}

	public void setIsCount(Integer isCount) {
		this.isCount = isCount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	
	public String getPersonPwd() {
		return personPwd;
	}

	public void setPersonPwd(String personPwd) {
		this.personPwd = personPwd;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}


}