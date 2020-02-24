package com.wuxin.appservice.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuxin.appservice.model.Dept;
import com.wuxin.appservice.model.OperationInfo;
import com.wuxin.appservice.model.Person;
import com.wuxin.appservice.model.User;
import com.wuxin.appservice.service.DeptService;
import com.wuxin.appservice.service.OperationInfoService;
import com.wuxin.appservice.service.PersonService;
import com.wuxin.appservice.service.UserService;
import com.wuxin.appservice.util.DateUtil;
import com.wuxin.appservice.util.RedisUtil;
import com.wuxin.appservice.util.ReturnResultUtil;
import com.wuxin.appservice.vo.enums.DeptVo;

/**
 * 部门管理的控制器
 * @author hk
 *
 */
@Controller
@RequestMapping("dept")
public class DeptController {
	/** 日志打印*/
	protected Logger logger = Logger.getLogger(getClass());
	@Autowired
	private DeptService deptService;
	@Autowired
	private UserService userService;
	@Autowired
	private PersonService personService;
	@Autowired
	OperationInfoService operationInfoService;
	OperationInfo operationInfo=new OperationInfo();
	/**
	 * 进入部门页面
	 * @param request
	 * @param response
	 * @return
	 * @author hk
	 * @Data 2017/7/19
	 */
	@RequestMapping(value="page",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	public String page(HttpServletRequest request,HttpServletResponse response){
		return "/page/dept/page";
	}
	/**
	 * 分页查询所有的方法
	 * @param request
	 * @param response
	 * @param pv
	 * @return
	 */
	@RequestMapping(value="list",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil list(HttpServletRequest request,HttpServletResponse response,DeptVo dv){
		DeptVo deptVo =new DeptVo();
		try {
			//得到redis中的当前登录用户
			User user =(User)request.getSession().getAttribute("loginUser");
			//判断用户不为空
			if(user!=null){
					
				//根据创办人id查询该创办人所创键的员工
				dv.setCreateBy(user.getUid());
				//判断为超级管理员
				 if(user.getRoleId()!=1){
					 dv.setCreateBy(null);
				 }
				//根据创建人id查询所对应的员工
				List<DeptVo> list = deptService.list(dv);
				//查询所有管理员状态为0的，以sql中加入了条件，用做添加中的部门下拉框
				List<User> users = userService.selectUser();
				//查询总条数
				int count = deptService.count(dv);
				deptVo.setList(list);
				deptVo.setTotal(count);
				deptVo.setUserList(users);
				
				OperationInfo  oper= operation(request);
				//被操作人id
				 oper.setUpdateId(user.getUid());
				 oper.setContent("部门查询");
				 //修改前时间
				 oper.setOldvalue("查询前："+DateUtil.DateTimeToStr(new Date()));
				 //修改后时间
				 oper.setNewvalue("查询后:"+DateUtil.DateTimeToStr(new Date()));
				 operationInfoService.insertSelective(oper);
				 
				return new ReturnResultUtil(200,"查讯成功",deptVo);
			}else{
				return new ReturnResultUtil(500,"登录失效请重新登录");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"",deptVo);
		}
		
	}
	/**
	 * 添加部门和修改部门的方法
	 * @param request
	 * @param response
	 * @param pv
	 * @return
	 */
	@RequestMapping(value="deptAdd",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil deptAdd(HttpServletRequest request,HttpServletResponse response,Dept dv) {
		dv.setCreateDate(DateUtil.DateTimeToStr(new Date()));
		dv.setUpdateDate(DateUtil.DateTimeToStr(new Date()));
		//得到redis中的当前登录用户
		User user =(User)request.getSession().getAttribute("loginUser");
		try {
			if(user!=null){
				if(dv.getDid()!=null){
					//从redis中取出用户id得到修改人id
					 dv.setUpdateBy(user.getUid());
					//通过部门id查询该部门
					Dept de = deptService.get(dv.getDid());
					 int i = deptService.updateByPrimaryKeySelective(dv);
					if(i>0){
						
						 OperationInfo  oper= operation(request);
						//被操作人id
						 oper.setUpdateId(de.getDid());
						 oper.setContent("部门修改");
						 //修改前时间
						 oper.setOldvalue("修改前："+de.getUpdateDate());
						 //修改后时间
						 oper.setNewvalue("修改后:"+dv.getUpdateDate());
						 operationInfoService.insertSelective(oper);
						return new ReturnResultUtil(200,"修改成功");
					}else{  
						return new ReturnResultUtil(200,"修改失败");
					}
				}else{
					//从redis中取出用户id得到创建人id
					dv.setCreateBy(user.getUid());
					dv.setUpdateBy(user.getUid());
					dv.setStatus(1);
					//判断如果是管理员登录则添加的部门属于自己
					if(user.getRoleId()==1){
						dv.setUid(user.getUid());
					}
					int i = deptService.insertSelective(dv);
					if(i>0){
						
						OperationInfo  oper= operation(request);
						 //被操作人id
						 oper.setUpdateId(dv.getDid());
						 oper.setContent("部门添加");
						 //添加前时间
						 oper.setOldvalue("添加前："+dv.getCreateDate());
						 //添加后时间
						 oper.setNewvalue("添加后:"+dv.getUpdateDate());
						 operationInfoService.insertSelective(oper);
						return new ReturnResultUtil(200,"添加成功");
					}else{
						return new ReturnResultUtil(200,"添加失败");
					}
				}
			}else{
				return new ReturnResultUtil(500,"登录失效请重新登录");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"添加异常");
		}
	} 
	/**
	 * 通过id查询所有  做修改
	 * @param request
	 * @param response
	 * @param pid  部门id
	 * @return  
	 */
	@RequestMapping(value="get",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil get(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer did){
		
		try {
			//通过部门id查询该部门
			Dept dept = deptService.get(did);
			return new ReturnResultUtil(200,"查询成功",dept);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"查询失败");
		}
		
	}
	/**
	 * 把正常使用改为禁止使用的方法
	 * @param request
	 * @param response
	 * @param pid
	 * @return
	 */
	@RequestMapping(value="del",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil del(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer did){
		//得到redis中的当前登录用户	
		User user =(User)request.getSession().getAttribute("loginUser");
		try {
			//通过部门id查询该部门
			Dept dept = deptService.get(did);
			Dept de = deptService.get(did);
			Person  person=new Person();
			//判断不为空
			if(dept!=null){
				person.setDeptId(dept.getDid());
				//查询该部门是否还有无员工，无则可被禁用
				List<Person> p=personService.selectPer(person);
				if(p.size()==0){
					dept.setStatus(2);
					dept.setUpdateDate(DateUtil.DateTimeToStr(new Date()));
					//从redis中取出用户id得到修改人id
					dept.setUpdateBy(user.getUid());
					
					 int i = deptService.updateByPrimaryKeySelective(dept);
					 if(i>0){
						 
						 OperationInfo  oper= operation(request);
						//被操作人id
						 oper.setUpdateId(de.getDid());
						 oper.setContent("部门状态修改");
						 //修改前时间
						 oper.setOldvalue("修改前："+de.getStatus());
						 //修改后时间
						 oper.setNewvalue("修改后:"+dept.getStatus());
						 operationInfoService.insertSelective(oper);
						 return new ReturnResultUtil(200,"禁用成功",dept);
					 }else{
						 return new ReturnResultUtil(500,"禁用失败",dept);
					 }
				}else{
					for (Person per : p) {
						if(per.getStatus()==0){
							return new ReturnResultUtil(500,"该部门还有员工未禁用",person);
						}
					}
				}
			}else{
				return new ReturnResultUtil(500,"查询失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			operation(request);
			return new ReturnResultUtil(500,"查询失败");
		}
		return new ReturnResultUtil(500,"查询失败");
		
	}
	/**
	 * 把禁止使用改为正常使用的方法
	 * @param request
	 * @param response
	 * @param pid
	 * @return
	 */
	@RequestMapping(value="del1",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil del1(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer did){
		User user =(User)request.getSession().getAttribute("loginUser");
		try {
			Dept dept = deptService.get(did);
			Dept de = deptService.get(did);
			dept.setStatus(1);
			dept.setUpdateDate(DateUtil.DateTimeToStr(new Date()));
			//从redis中取出用户id得到修改人id
			dept.setUpdateBy(user.getUid());
			 int i = deptService.updateByPrimaryKeySelective(dept);
			 if(i>0){
				 
				 OperationInfo  oper= operation(request);
					//被操作人id
					 oper.setUpdateId(de.getDid());
					 oper.setContent("部门状态修改");
					 //修改前时间
					 oper.setOldvalue("修改前："+de.getStatus());
					 //修改后时间
					 oper.setNewvalue("修改后:"+dept.getStatus());
					 operationInfoService.insertSelective(oper);
				 return new ReturnResultUtil(200,"解锁成功",dept);
			 }else{
				 return new ReturnResultUtil(500,"解锁失败",dept);
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"查询失败");
		}
		
	}
	/**
	 * 记录登录用户操作记录方法
	 * @param request
	 */
	public OperationInfo operation(HttpServletRequest request){
		User user =(User)request.getSession().getAttribute("loginUser");
		//管理员id
		operationInfo.setUid(user.getUid());
		//1：操作类别
		operationInfo.setType(1);
		//operationInfo.setStatus(1);
		//修改前
		//operationInfo.setUpdateId(user.getUid());
		//修改后
		//operationInfo.setContent("员工操作");
		//创建人
		operationInfo.setCreateBy(user.getUid());
		//修改人
		operationInfo.setUpdateBy(user.getUid());
		//操作时间
		operationInfo.setCreateDate(DateUtil.DateTimeToStr(new Date()));
		operationInfo.setStatus("");
		return operationInfo;
	}
}
	

