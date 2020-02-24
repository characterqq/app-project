package com.wuxin.appservice.dao;

import java.util.List;

import com.wuxin.appservice.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> list();
    
    List<Role> lists(Role record);
    
    int count(Role record);
}