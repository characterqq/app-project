package com.wuxin.appservice.dao;

import java.util.List;
import java.util.Map;

import com.wuxin.appservice.model.Person;
import com.wuxin.appservice.vo.enums.PersonVo;

public interface PersonMapper {
	//根据警号查询数据库是否有相同的警号
	Person personAll(Person p);
	//通过员工警号和密码验证登录
	Person loginEquipment(Person per);
	
	/**
	 * 根据登录的警号和密码更新设备id号 
	 * @param person
	 * @return
	 */
	int updatePoliceEID(Person person);
	
    int deleteByPrimaryKey(Integer pid);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
    
    int selectCount(Map<String, Object> map);
    
    List<Person> personPages(Map<String, Object> map)throws Exception;
    
    List<Person> lists(Person p);
    
    Person get(Integer pid);
    
   List<Person> selectPer(Person p);
   /**
    * 查询人员警号
    * @param pid
    * @return
    */
   String policeNo(Integer pid);
    
    /**
   	 * 用户列表
   	 * @param pv
   	 * @author hk
   	 * @return
   	 */
       List<PersonVo> list (PersonVo pv);
       /**
        * 用户总数
        * @param pv
        * @return
        */
       int count (PersonVo pv);
       
       Person getPoliceNo(String policeNo);
       
       List<PersonVo> onLinePersonTime (PersonVo pv);
       
       List<PersonVo> forbiddenPerson (PersonVo pv);
       
       List<PersonVo> onLinePerson (PersonVo pv);
       
       int sum ();
       
}