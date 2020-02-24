package com.wuxin.appservice.service;

import java.util.List;

import com.wuxin.appservice.model.Role;


public interface RoleService {
	
	/**
	 * 角色列表--下拉框
	 * @param uv
	 * @author hn
	 * @return
	 */
    List<Role> list ();
    /**
     * 角色列表 
     * @param record
     * @return
     */
    List<Role> lists(Role record);
    /**
     * 角色总数
     * @param record
     * @return
     */
    int count(Role record);
    /**
     * 角色删除
     * @param rid
     * @return
     */
    int deleteByPrimaryKey(Integer rid);
    /**
     * 角色添加
     * @param rid
     * @return
     */
    int insert(Role record);
    /**
     * 角色信息
     * @param rid
     * @return
     */

    Role selectByPrimaryKey(Integer rid);
    /**
     * 角色修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Role record);
 
}
