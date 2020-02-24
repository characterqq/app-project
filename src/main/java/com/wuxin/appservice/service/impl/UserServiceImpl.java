package com.wuxin.appservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuxin.appservice.dao.RoleMapper;
import com.wuxin.appservice.dao.UserMapper;
import com.wuxin.appservice.model.Role;
import com.wuxin.appservice.model.User;
import com.wuxin.appservice.service.UserService;
import com.wuxin.appservice.vo.enums.UserVo;
@Service("UserService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 用户列表
	 * @param uv
	 * @author hn
	 * @return
	 */
	@Override
	public List<UserVo> list(UserVo uv) {
		
		
		return userMapper.list(uv);
	}
	   /**
     * 用户总数
     * @param uv
     * @return
     */
	@Override
	public int count(UserVo uv) {
		// TODO Auto-generated method stub
		return userMapper.count(uv);
	}
	/**
     *用户添加
     * @param record
     * @return
     */
	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return userMapper.insertSelective(record);
	}
	/**
     * 用户详情
     * @param uid
     * @return
     */
	@Override
	public UserVo get(Integer uid) {
		// TODO Auto-generated method stub
		return userMapper.get(uid);
	}
	  /**
     * 用户修改
     * @param record
     * @return
     */
	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public List<User> selectUser() {
		// TODO Auto-generated method stub
		return userMapper.selectUser();
	}
	@Override
	public UserVo loginUser(UserVo uv) {
		// TODO Auto-generated method stub
		return userMapper.loginUser(uv);
	}
	@Override
	public int updateUserPwd(UserVo uv) {
		// TODO Auto-generated method stub
		return userMapper.updateUserPwd(uv);
	}
	/**
	 * 绑定设备
	 */
	@Override
	public UserVo binDingEquipment(UserVo uv) {
		// TODO Auto-generated method stub
		return userMapper.binDingEquipment(uv);
	}
	/**
	 * 判断用户名是否重复
	 */
	@Override
	public int isName(String isName) {
		// TODO Auto-generated method stub
		return userMapper.isName(isName);
	}

}
