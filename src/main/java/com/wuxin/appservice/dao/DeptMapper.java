package com.wuxin.appservice.dao;

import java.util.List;

import com.wuxin.appservice.model.Dept;
import com.wuxin.appservice.vo.enums.DeptVo;

public interface DeptMapper {
    int deleteByPrimaryKey(Integer did);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Integer did);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);
    
    List<Dept> selectDept();
    
    Dept uidDept(Integer uid);
    
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
}