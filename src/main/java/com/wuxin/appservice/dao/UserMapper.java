package com.wuxin.appservice.dao;

import java.util.List;

import com.wuxin.appservice.model.User;
import com.wuxin.appservice.vo.enums.UserVo;

public interface UserMapper {
	/**
	 * 绑定设备
	 * @param uv
	 * @return
	 */
	UserVo binDingEquipment(UserVo uv);
	
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    UserVo get(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<UserVo> list(UserVo uv);
    
    int count (UserVo uv);
    
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