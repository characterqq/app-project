package com.wuxin.appservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuxin.appservice.dao.UserMapper;
import com.wuxin.appservice.model.OperationInfo;
import com.wuxin.appservice.model.User;
import com.wuxin.appservice.service.OperationInfoService;
import com.wuxin.appservice.service.RoleService;
import com.wuxin.appservice.service.UserService;
import com.wuxin.appservice.util.DateUtil;
import com.wuxin.appservice.util.RedisUtil;
import com.wuxin.appservice.util.ReturnResultUtil;
import com.wuxin.appservice.vo.enums.UserVo;

/**
 * 部门管理的控制器
 * @author hn
 *
 */
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	OperationInfoService operationInfoService;
	/**
	 * 进入用户页面
	 * @param request
	 * @param response
	 * @return
	 * @author hn
	 * @Data 2017/7/19
	 */
	@RequestMapping(value="page",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	public String page(HttpServletRequest request,HttpServletResponse response){
		return "/page/user/page";
	}
	
	@RequestMapping(value="list",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil list(HttpServletRequest request,HttpServletResponse response,UserVo uv){
		UserVo userVo =new UserVo();
		List<UserVo> list=null;
		int count=0;
		try {
			
			User user =(User)request.getSession().getAttribute("loginUser");
			if(user!=null){
				if(user.getRoleId()==2){
					
					 list = userService.list(uv);
					 count =  userService.count(uv);
					}else{
					uv.setCreateBy(user.getUid());
					 list =userService.list(uv);
					 count =  userService.count(uv);
					}
				
			
			userVo.setList(list);
			userVo.setTotal(count);
			userVo.setRoleList(roleService.list());
			return new ReturnResultUtil(200,"查询成功",userVo);
			}else{
				return new ReturnResultUtil(500,"登录失效，请重新登录");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"查询失败",userVo);
		}
		
	}
	@RequestMapping(value="addorupdate",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil addorupdate(HttpServletRequest request,HttpServletResponse response,User uv){
		OperationInfo record =new OperationInfo();
		
		uv.setCreateDate(DateUtil.DateTimeToStr(new Date()));
		uv.setUpdateDate(DateUtil.DateTimeToStr(new Date()));
		
		try {
			User user =(User)request.getSession().getAttribute("loginUser");
			if(user!=null){
			
			if(uv.getUid()!=null){
				uv.setUpdateBy(user.getUid());
				uv.setUpdateDate(DateUtil.DateTimeToStr(new Date()));
				int i = userService.updateByPrimaryKeySelective(uv);
				if(i>0){
					record.setUid(user.getUid());
					record.setType(3);
					record.setContent(user.getUserName()+"修改了用户");
					record.setUpdateId(uv.getUid());
					record.setCreateDate(DateUtil.DateTimeToStr(new Date()));
					int o = operationInfoService.insertSelective(record);
					if(o>0){
						return new ReturnResultUtil(200,"修改成功");
					}else{
						return new ReturnResultUtil(200,"修改失败");
					}
				}else{  
					   return new ReturnResultUtil(200,"修改失败");
				}
			}else{
				uv.setCreateBy(user.getUid());
				uv.setUpdateBy(user.getUid());
				uv.setStatus(0);
				int i = userService.insertSelective(uv);
				if(i>0){
					record.setUid(user.getUid());
					record.setType(3);
					record.setContent(user.getUserName()+"添加了用户");
					record.setUpdateId(uv.getUid());
					record.setCreateDate(DateUtil.DateTimeToStr(new Date()));
					int o = operationInfoService.insertSelective(record);
					if(o>0){
						return new ReturnResultUtil(200,"添加成功");
					}else{
						return new ReturnResultUtil(200,"修改失败");
					}
					
				}else{
					return new ReturnResultUtil(200,"添加失败");
				}
			}
			
			}else{
				return new ReturnResultUtil(500,"登录失效，请重新登录");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"添加异常");
		}
		
	}
	@RequestMapping(value="get",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil get(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer uid){
		
		try {
			User user = userService.get(uid);
		
			return new ReturnResultUtil(200,"查询成功",user);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"查询失败");
		}
		
	}
	@RequestMapping(value="del",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil del(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer uid){
		
		try {
			User user = userService.get(uid);
			user.setStatus(1);
			user.setUpdateDate(DateUtil.DateTimeToStr(new Date()));
			 int i = userService.updateByPrimaryKeySelective(user);
			 if(i>0){
				 return new ReturnResultUtil(200,"禁用成功",user);
			 }else{
				 return new ReturnResultUtil(500,"禁用失败",user);
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"查询失败");
		}
		
	}
	@RequestMapping(value="del1",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil del1(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer uid){
		OperationInfo record =new OperationInfo();
		try {
			User user = userService.get(uid);
			user.setStatus(0);
			user.setUpdateDate(DateUtil.DateTimeToStr(new Date()));
			 int i = userService.updateByPrimaryKeySelective(user);
			 if(i>0){
				 	record.setUid(user.getUid());
					record.setType(3);
					record.setContent(user.getUserName()+"禁用了用户");
					record.setUpdateId(uid);
					record.setCreateDate(DateUtil.DateTimeToStr(new Date()));
					int o = operationInfoService.insertSelective(record);
					if(o>0){
						 return new ReturnResultUtil(200,"禁用成功",user);
					}else{
						return new ReturnResultUtil(200,"修改失败");
					}
				
			 }else{
				 return new ReturnResultUtil(500,"禁用失败",user);
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"查询失败");
		}
		
	}
	
	@RequestMapping(value="isName",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil isName(HttpServletRequest request,HttpServletResponse response,@RequestParam String name){
		try {
			 int i = userService.isName(name);
			if(i>0){

				 return new ReturnResultUtil(500,"该用户名已存在",i);
			}else{
				 return new ReturnResultUtil(500,"可添加",i);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"查询失败");
		}
		
	}
	
	
}
