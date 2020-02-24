package com.wuxin.appservice.vo.enums;

import java.io.Serializable;
import java.util.List;

import com.wuxin.appservice.model.Dept;
import com.wuxin.appservice.model.Person;
/**
 * 员工分页枚举类
 * @author hk
 *
 */
public class PersonVo extends Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dname;
	
	private Integer did;
	
	private Integer count;
	
	private Integer hour;
	
	private String starDate;

	private String endDate;
	
	private Dept deptvo;
	
	private List<PersonVo> personVolist;
	
	private List<Dept> deptList;
	public Dept getDeptvo() {
		return deptvo;
	}
	public void setDeptvo(Dept deptvo) {
		this.deptvo = deptvo;
	}
	public List<Dept> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<Dept> deptList) {
		this.deptList = deptList;
	}
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
	public List<PersonVo> getPersonVolist() {
		return personVolist;
	}
	public void setPersonVoVolist(List<PersonVo> personVolist) {
		this.personVolist = personVolist;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setPersonVolist(List<PersonVo> personVolist) {
		this.personVolist = personVolist;
	}
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
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
	
}
