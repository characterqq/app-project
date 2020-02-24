package com.wuxin.appservice.vo.enums;

import com.wuxin.appservice.model.Person;

public class InterfaceResultVo {
	/** 查询是否成功 */
	private boolean isSuccess;
	/** 处理结果描述 */
	private String message;
	
	/** 用户信息 */
	private PersonVo personVo;
	
	private Person person;
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PersonVo getPersonVo() {
		return personVo;
	}

	public void setPersonVo(PersonVo personVo) {
		this.personVo = personVo;
	}


	
	
	
	
}
