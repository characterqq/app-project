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
import com.wuxin.appservice.model.Equipment;
import com.wuxin.appservice.model.OperationInfo;
import com.wuxin.appservice.model.Person;
import com.wuxin.appservice.model.User;
import com.wuxin.appservice.service.DeptService;
import com.wuxin.appservice.service.EquipmentService;
import com.wuxin.appservice.service.OperationInfoService;
import com.wuxin.appservice.service.PersonService;
import com.wuxin.appservice.util.DateUtil;
import com.wuxin.appservice.util.ReturnResultUtil;
import com.wuxin.appservice.vo.enums.EquipmentVo;
import com.wuxin.appservice.vo.enums.PersonVo;

/**
 * 人员管理的控制器
 * @author hk
 *
 */
@Controller
@RequestMapping("person")
public class PersonController {
	/** 日志打印*/
	protected Logger logger = Logger.getLogger(getClass());
	@Autowired
	private PersonService persons;
	@Autowired
	private DeptService dept;
	@Autowired
	private EquipmentService eqService;
	@Autowired
	OperationInfoService operationInfoService;
	OperationInfo operationInfo=new OperationInfo();
	/**
	 * 添加员工和修改员工的方法
	 * @param request
	 * @param response
	 * @param pv
	 * @return
	 */
	@RequestMapping(value="personAdd",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil personAdd(HttpServletRequest request,HttpServletResponse response,Person pv) {
		//得到当前时间
		pv.setCreateDate(DateUtil.DateTimeToStr(new Date()));
		pv.setUpdateDate(DateUtil.DateTimeToStr(new Date()));
		//证书
		String publickey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDcCfd+c2OPZmVcIw1xEsSi95BrrC/"+
	  			"IgGCc4c3qF2wo+HEUzHHx5FuXyhpRbd7gbc3czVwzcuYKUngurt0ADpkLqhIF3rvs2sS5MhUbxs0"
	  			+ "zIFaohg4Fi53LpxooqT+69JyVxbezZhoaFomNDqWnHFtlfHBK4P64hq8jjEdnXAjWxwIDAQAB";

		//从redis中获取对象
		User user =(User)request.getSession().getAttribute("loginUser");
		logger.info("==========将用户名和id从redis取出来========"+user.getUserName()+"===="+user.getUid());
		
		try {
			//判断redis中对象不为空
			if(user!=null){
				//员工id不为空
				if(pv.getPid()!=null){
					//从redis中取出用户id得到修改人id
					 pv.setUpdateBy(user.getUid());
					 //哪个管理员登录的就只能修改属于该用户管理的员工
					 int i = persons.updateByPrimaryKeySelective(pv);
						//通过员工id查询该员工
						Person pers = persons.get(pv.getPid());
					if(i>0){
						//操作详情对象
						 OperationInfo  oper= operation(request);
						 //被操作人id
						 oper.setUpdateId(pv.getPid());
						 //备注
						 oper.setContent("员工修改");
						 //修改前时间
						 oper.setOldvalue("修改前："+pers.getUpdateDate());
						 //修改后时间
						 oper.setNewvalue("修改后:"+pv.getUpdateDate());
						 //添加操作表
						 operationInfoService.insertSelective(oper);
						return new ReturnResultUtil(200,"修改成功");
					}else{  
						
						return new ReturnResultUtil(200,"修改失败");
					}
				}else{
					//从redis中取出用户id得到创建人id
					pv.setCreateBy(user.getUid());
					//修改人id
					pv.setUpdateBy(user.getUid());
					pv.setStatus(0);
					pv.setPublicKey(publickey);
					//根据警号查询员工
					Person p = persons.personAll(pv);
					if(p!=null){
						return new ReturnResultUtil(500,"警号已存在");
					}else{
						//调用添加员工的方法，插入创建人id
						int i = persons.insertSelective(pv);
						if(i>0){
						 	OperationInfo  oper= operation(request);
							 //被操作人id
							 oper.setUpdateId(pv.getPid());
							 oper.setContent("员工添加");
							 //添加前时间
							 oper.setOldvalue("添加前："+pv.getCreateDate());
							 //添加后时间
							 oper.setNewvalue("添加后:"+pv.getUpdateDate());
							 operationInfoService.insertSelective(oper);
							return new ReturnResultUtil(200,"添加成功");
						}else{
							return new ReturnResultUtil(500,"添加失败");
						}
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
	 * 分页查询所有的方法
	 * @param request
	 * @param response
	 * @param pv 封装的分页类
	 * @return
	 */
	@RequestMapping(value="list",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil list(HttpServletRequest request,HttpServletResponse response,PersonVo pv){
		PersonVo personVo =new PersonVo();
		try {
			//得到redis中的当前登录用户
			User user =(User)request.getSession().getAttribute("loginUser");
			//根据创办人id查询该创办人所创键的员工
			pv.setCreateBy(user.getUid());
			if(user!=null){
				System.out.println(user.getUid()+"uid================");
				//判断为超级管理员和普通员工
				 if(user.getRoleId()!=1){
					 pv.setCreateBy(null);
					//根据当前登录人查询所管理的部门。显示在添加的部门下拉框
					List<Dept> depts = dept.selectDept();
					personVo.setDeptList(depts);
				 }else{
					 //根据uid查询该管理员所属部门
					 Dept depts = dept.uidDept(user.getUid());
					 personVo.setDeptvo(depts);
				 }
			//查询所有员工
			List<PersonVo> list = persons.list(pv);
			//查询总条数
			int count = persons.count(pv);
			personVo.setList(list);
			personVo.setTotal(count);
			
			OperationInfo  oper= operation(request);
			//被操作人id
			 oper.setUpdateId(user.getUid());
			 oper.setContent("员工查询");
			 //修改前时间
			 oper.setOldvalue("查询前："+DateUtil.DateTimeToStr(new Date()));
			 //修改后时间
			 oper.setNewvalue("查询后:"+DateUtil.DateTimeToStr(new Date()));
			 operationInfoService.insertSelective(oper);
			return new ReturnResultUtil(200,"查询成功",personVo);
			}else{
				return new ReturnResultUtil(500,"登录失效请重新登录");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"",personVo);
		}
		
	}
	/**
	 * 进入人员页面
	 * @param request
	 * @param response
	 * @return
	 * @author hn
	 * @Data 2017/7/19
	 */
	@RequestMapping(value="page",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	public String page(HttpServletRequest request,HttpServletResponse response){
		return "/page/person/page";
	}
	/**
	 * 通过id查询所有  做修改
	 * @param request
	 * @param response
	 * @param pid  员工id
	 * @return  
	 */
	@RequestMapping(value="get",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil get(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer pid){
		
		try {
			Person person = persons.get(pid);
			return new ReturnResultUtil(200,"查询成功",person);
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
	public ReturnResultUtil del(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer pid){
			//从redis中获取当前登录对象
		User user =(User)request.getSession().getAttribute("loginUser");
			Equipment equs=new Equipment();
			//通过员工id查询该员工
			Person pers = persons.get(pid);
		try {
			//通过员工id查询该员工
			Person person = persons.get(pid);
			if(person!=null){
				//写入员工id
				equs.setPid(person.getPid());
				//redis中用户id作为条件写入
				equs.setUpdateBy(user.getUid());
				//查询设备表查看该员工是否拥有设备，
				 List<EquipmentVo> equ = eqService.selectEquipment(equs);
				if(equ.size()!=0){
					for (Equipment per : equ) {
						if(per.getStatus()!=2){
							operation(request);
							return new ReturnResultUtil(500,"该用户拥有设备不可禁用",person);
						}
					}
					
				} else{
					person.setStatus(1);
					person.setUpdateDate(DateUtil.DateTimeToStr(new Date()));
					//从redis中取出用户id得到修改人id
					person.setUpdateBy(user.getUid());
					//调用修改员工状态的方法，同时也加入修改人和修改日期
					 int i = persons.updateByPrimaryKeySelective(person);
					 if(i>0){
						 OperationInfo  oper= operation(request);
						 oper.setUpdateId(pers.getPid());
						 oper.setContent("员工状态修改");
						 //修改前时间
						 oper.setOldvalue("修改前："+pers.getStatus());
						 //修改后时间
						 oper.setNewvalue("修改后:"+person.getStatus());
						 operationInfoService.insertSelective(oper);
						 return new ReturnResultUtil(200,"禁用成功",person);
					 }else{
						 operation(request);
						 return new ReturnResultUtil(500,"禁用失败",person);
					 }
				}
				
			
			}else{
				operation(request);
				return new ReturnResultUtil(500,"查询失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			operation(request);
			return new ReturnResultUtil(500,"查询失败");
		}
		operation(request);
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
	public ReturnResultUtil del1(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer pid){
		//从redis获取当前登录对象
		User user =(User)request.getSession().getAttribute("loginUser");
		//通过员工id查询该员工
		Person pers = persons.get(pid);
		try {
			//通过员工id查询所对应的员工
			Person person = persons.get(pid);
			person.setStatus(0);
			person.setUpdateDate(DateUtil.DateTimeToStr(new Date()));
			//从redis中取出用户id得到修改人id
			person.setUpdateBy(user.getUid());
			//调用修改员工状态的方法，同时也加入修改人和修改日期
			 int i = persons.updateByPrimaryKeySelective(person);
			 if(i>0){
				 OperationInfo  oper= operation(request);
				 oper.setUpdateId(pers.getPid());
				 oper.setContent("员工状态修改");
				 //修改前状态
				 oper.setOldvalue("修改前："+pers.getStatus());
				 //修改后状态
				 oper.setNewvalue("修改后:"+person.getStatus());
				 operationInfoService.insertSelective(oper);
				 return new ReturnResultUtil(200,"解锁成功",person);
			 }else{
				 return new ReturnResultUtil(500,"解锁失败",person);
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			operation(request);
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
		operationInfo.setType(2);
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
	/**
	 * 人员总数
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="sum",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil sum(HttpServletRequest request,HttpServletResponse response){
		
		try {
			int sum  = persons.sum();
			return new ReturnResultUtil(200,"查询成功",sum);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"查询失败");
		}
		
	}
}
