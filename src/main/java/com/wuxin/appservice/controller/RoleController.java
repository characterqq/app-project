package com.wuxin.appservice.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuxin.appservice.model.OperationInfo;
import com.wuxin.appservice.model.Role;
import com.wuxin.appservice.model.User;
import com.wuxin.appservice.service.OperationInfoService;
import com.wuxin.appservice.service.RoleService;
import com.wuxin.appservice.util.DateUtil;
import com.wuxin.appservice.util.ReturnResultUtil;
import com.wuxin.appservice.vo.enums.UserVo;

/**
 * 部门管理的控制器
 * @author hn
 *
 */
@Controller
@RequestMapping("role")
public class RoleController {
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
		return "/page/role/page";
	}
	
	@RequestMapping(value="list",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil list(HttpServletRequest request,HttpServletResponse response,Role r){
		UserVo userVo =new UserVo();
		
		try {
			List<Role> list = roleService.lists(r);
			int count = roleService.count(r);
			userVo.setList(list);
			userVo.setTotal(count);
			userVo.setRoleList(roleService.list());
			return new ReturnResultUtil(200,"查询成功",userVo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"查询失败",userVo);
		}
		
	}
	@RequestMapping(value="addorupdate",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil addorupdate(HttpServletRequest request,HttpServletResponse response,Role r){
		
		OperationInfo record=new OperationInfo();
		
		try {
			User user =(User)request.getSession().getAttribute("loginUser");
			if(user!=null){
			if(r.getRid()!=null){
				int i = roleService.updateByPrimaryKeySelective(r);
				if(i>0){
					record.setUid(user.getUid());
					record.setType(3);
					record.setContent(user.getUserName()+"修改了角色");
					record.setUpdateId(r.getRid());
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
				int i = roleService.insert(r);
				if(i>0){
					record.setUid(user.getUid());
					record.setType(3);
					record.setContent(user.getUserName()+"添加了角色");
					record.setUpdateId(r.getRid());
					record.setCreateDate(DateUtil.DateTimeToStr(new Date()));
					int o = operationInfoService.insertSelective(record);
					if(o>0){
						return new ReturnResultUtil(200,"添加成功");
					}else{
						return new ReturnResultUtil(200,"添加失败");
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
	public ReturnResultUtil get(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer rid){
		
		try {
			Role r = roleService.selectByPrimaryKey(rid);
		
			return new ReturnResultUtil(200,"查询成功",r);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"查询失败");
		}
		
	}
	@RequestMapping(value="del",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil del(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer rid){
		
		try {
			 int i = roleService.deleteByPrimaryKey(rid);
			 if(i>0){
				 return new ReturnResultUtil(200,"删除成功");
			 }else{
				 return new ReturnResultUtil(500,"删除失败");
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"查询失败");
		}
		
	}
	
}
