package com.wuxin.appservice.service;

import java.util.List;
import java.util.Map;

import com.wuxin.appservice.model.Person;
import com.wuxin.appservice.vo.enums.PersonVo;

public interface PersonService {
	//根据警号查询数据库是否有相同的警号
	Person personAll(Person p);
	//通过员工警号和密码验证登录
	Person loginEquipment(Person per);
	
	List<Person> selectPer( Person p);
	
	 Person selectByPrimaryKey(Integer pid);
	 
	int insertSelective(Person record);
	
	int selectCount(Map<String, Object> map);
    
    public List<Person> personPages(Map<String, Object> map)throws Exception;
    /**
     * 人员下拉列表
     * @return
     */
    List<Person> lists(Person p);
    
    int deleteByPrimaryKey(Integer pid);
    
    Person get(Integer pid);
    
    int updateByPrimaryKeySelective(Person record);
    
    /**
	 * 员工列表
	 * @param pv
	 * @author hk
	 * @return
	 */
    List<PersonVo> list (PersonVo pv);
    /**
     * 员工总数
     * @param pv
     * @return
     */
    int count (PersonVo pv);
    /**
     * 通过警号获取人员信息
     * @param policeNo
     * @return
     */
    Person getPoliceNo(String policeNo);
    /**
     * 查询人员警号
     * @param pid
     * @return
     */
    String policeNo(Integer pid);
    
    /**
     * 统计当前时间点和过去时间段内各个部门人员在线数量 
     * @param pv
     * @return
     */
    List<PersonVo> onLinePersonTime (PersonVo pv);
    /**
     * 统计系统人员总数，禁用人员占总设备的比例。
     * @param pv
     * @return
     */
    List<PersonVo> forbiddenPerson (PersonVo pv);
    /**
     * 统计当前时间点和过去时间段内部门在线人数。
     * @param pv
     * @return
     */
    List<PersonVo> onLinePerson (PersonVo pv);
    /**
     * 人员总数
     * @return
     */
    int sum ();
}
