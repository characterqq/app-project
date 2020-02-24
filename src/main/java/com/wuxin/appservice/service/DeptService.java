package com.wuxin.appservice.service;

import java.util.List;

import com.wuxin.appservice.model.Dept;
import com.wuxin.appservice.vo.enums.DeptVo;

/**
 * 
 * @author hk
 *
 */
public interface DeptService {
	
	 Dept uidDept(Integer uid);
	 
	 List<Dept> selectDept();
	 
	 Dept get(Integer did);
	    /**
	   	 * 部门列表
	   	 * @param pv
	   	 * @author hk
	   	 * @return
	   	 */
	 List<DeptVo> list (DeptVo pv);
	     /**
	      * 部门总数
	      * @param pv
	      * @return
	      */
	 int count (DeptVo pv);
	 
	 int insertSelective(Dept record);
	 
	 int updateByPrimaryKeySelective(Dept record);
	 
	 int deleteByPrimaryKey(Integer did);
}
