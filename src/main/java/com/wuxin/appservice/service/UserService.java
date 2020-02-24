package com.wuxin.appservice.service;

import java.util.List;

import com.wuxin.appservice.model.User;
import com.wuxin.appservice.vo.enums.UserVo;

public interface UserService {
	/**
	 * 绑定设备
	 * @param uv
	 * @return
	 */
	UserVo binDingEquipment(UserVo uv);
	/**
	/**
	 * 用户列表
	 * @param uv
	 * @author hn
	 * @return
	 */
    List<UserVo> list (UserVo uv);
    /**
     * 用户总数
     * @param uv
     * @return
     */
    int count (UserVo uv);
    /**
     *用户添加
     * @param record
     * @return
     */
    int insertSelective(User record);
    /**
     * 用户详情
     * @param uid
     * @return
     */
    UserVo get(Integer uid);
    /**
     * 用户修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);
    
    List<User> selectUser();
    
    /**
     * 验证用户和密码
     * @param uv
     * @return
     */
    UserVo loginUser(UserVo uv);
    /**
     * 修改密码
     * @param uv
     * @return
     */
   int updateUserPwd(UserVo uv); 
   /**
    * 判断用户名是否重复
    * @param uv
    * @return
    */
   int isName(String isName); 
}
