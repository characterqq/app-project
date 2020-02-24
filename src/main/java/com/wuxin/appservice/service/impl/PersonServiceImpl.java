package com.wuxin.appservice.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuxin.appservice.dao.PersonMapper;
import com.wuxin.appservice.model.Person;
import com.wuxin.appservice.service.PersonService;
import com.wuxin.appservice.vo.enums.PersonVo;
@Service("PersonService")
public class PersonServiceImpl implements PersonService{
	@Autowired
	private PersonMapper  person;
	@Override
	public int selectCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return person.selectCount(map);
	}

	@Override
	public List<Person> personPages(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		
		return person.personPages(map);
	}
	/**
	 * 人员下拉列表
	 */
	@Override
	public List<Person> lists(Person p) {
		// TODO Auto-generated method stub
		return person.lists(p);
	}

	@Override
	public int insertSelective(Person record) {
		// TODO Auto-generated method stub
		return person.insertSelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer pid) {
		// TODO Auto-generated method stub
		return person.deleteByPrimaryKey(pid);
	}

	@Override
	public Person selectByPrimaryKey(Integer pid) {
		// TODO Auto-generated method stub
		return person.selectByPrimaryKey(pid);
	}
	/**
	 * 用户列表
	 * @param uv
	 * @author hk
	 * @return
	 */
	@Override
	public List<PersonVo> list(PersonVo pv) {
		// TODO Auto-generated method stub
		return person.list(pv);
	}
	   /**
     * 用户总数
     * @param uv
     * @return
     */
	@Override
	public int count(PersonVo pv) {
		// TODO Auto-generated method stub
		return person.count(pv);
	}

	@Override
	public Person get(Integer pid) {
		// TODO Auto-generated method stub
		return person.get(pid);
	}

	@Override
	public int updateByPrimaryKeySelective(Person record) {
		// TODO Auto-generated method stub
		return person.updateByPrimaryKeySelective(record);
	}
	 /**
     * 通过警号获取人员信息
     * @param policeNo
     * @return
     */
	@Override
	public Person getPoliceNo(String policeNo) {
		// TODO Auto-generated method stub
		return person.getPoliceNo(policeNo);
	}

	@Override
	public List<Person> selectPer(Person p) {
		// TODO Auto-generated method stub
		return person.selectPer(p);
	}

	@Override
	public String policeNo(Integer pid) {
		// TODO Auto-generated method stub
		return person.policeNo(pid);
	}
	 /**
     * 统计当前时间点和过去时间段内各个部门人员在线数量 
     * @param pv
     * @return
     */
	@Override
	public List<PersonVo> onLinePersonTime(PersonVo pv) {
		// TODO Auto-generated method stub
		return person.onLinePersonTime(pv);
	}
	  /**
     * 统计系统人员总数，禁用人员占总设备的比例。
     * @param pv
     * @return
     */
	@Override
	public List<PersonVo> forbiddenPerson(PersonVo pv) {
		// TODO Auto-generated method stub
		return person.forbiddenPerson(pv);
	}
	 /**
     * 统计当前时间点和过去时间段内部门在线人数。
     * @param pv
     * @return
     */
	@Override
	public List<PersonVo> onLinePerson(PersonVo pv) {
		// TODO Auto-generated method stub
		return person.onLinePerson(pv);
	}

	@Override
	public Person loginEquipment(Person per) {
		// TODO Auto-generated method stub
		Person mPerson = person.loginEquipment(per); 
		return mPerson;
	}
	/**
	 * 	根据警号查询数据库是否有相同的警号
	 */
	@Override
	public Person personAll(Person p) {
		// TODO Auto-generated method stub
		return person.personAll(p);
	}
/**
 * 人员总数
 */
	@Override
	public int sum() {
		// TODO Auto-generated method stub
		return person.sum();
	}

}
