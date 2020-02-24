package com.wuxin.appservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuxin.appservice.dao.RoleMapper;
import com.wuxin.appservice.model.Role;
import com.wuxin.appservice.service.RoleService;

@Service("RoleService")
public class RoleServiceImpl  implements RoleService{
	@Autowired
	private RoleMapper roleMapper;
	
	/**
	 * 角色列表
	 * @param uv
	 * @author hn
	 * @return
	 */
	@Override
	public List<Role> list() {
		// TODO Auto-generated method stub
		return roleMapper.list();
	}
	 /**
     * 角色列表 
     * @param record
     * @return
     */
	@Override
	public List<Role> lists(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.lists(record);
	}
	  /**
     * 角色总数
     * @param record
     * @return
     */
	@Override
	public int count(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.count(record);
	}
	  /**
     * 角色删除
     * @param rid
     * @return
     */
	@Override
	public int deleteByPrimaryKey(Integer rid) {
		// TODO Auto-generated method stub
		return roleMapper.deleteByPrimaryKey(rid);
	}
	 /**
     * 角色添加
     * @param rid
     * @return
     */
	@Override
	public int insert(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.insert(record);
	}
	 /**
     * 角色信息
     * @param rid
     * @return
     */

	@Override
	public Role selectByPrimaryKey(Integer rid) {
		// TODO Auto-generated method stub
		return roleMapper.selectByPrimaryKey(rid);
	}
	/**
     * 角色修改
     * @param record
     * @return
     */
	@Override
	public int updateByPrimaryKeySelective(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.updateByPrimaryKey(record);
	}

  
  
}
