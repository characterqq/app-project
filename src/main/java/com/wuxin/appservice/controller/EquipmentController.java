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
import com.wuxin.appservice.model.Person;
import com.wuxin.appservice.model.User;
import com.wuxin.appservice.service.EquipmentService;
import com.wuxin.appservice.service.OperationInfoService;
import com.wuxin.appservice.service.PersonService;
import com.wuxin.appservice.service.UserService;
import com.wuxin.appservice.util.DateUtil;
import com.wuxin.appservice.util.ReturnResultUtil;
import com.wuxin.appservice.vo.enums.EquipmentVo;


/**
 * 设备管理的控制器
 * @author hn
 *
 */
@Controller
@RequestMapping("equipment")
public class EquipmentController {
	@Autowired
	EquipmentService equipmentService;
	
	@Autowired
	OperationInfoService operationInfoService;
	
	@Autowired
	PersonService personService;
	
	@Autowired
	UserService userService;
	/**
	 * 进入设备页面
	 * @param request
	 * @param response
	 * @return
	 * @author hn
	 * @Data 2017/7/25
	 */
	@RequestMapping(value="page",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	public String page(HttpServletRequest request,HttpServletResponse response){
		return "/page/equipment/page";
	}
	
	@RequestMapping(value="list",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil list(HttpServletRequest request,HttpServletResponse response,EquipmentVo equipment){
		EquipmentVo equipmentVo =new EquipmentVo();
		List<EquipmentVo> list=null;
		List<Person> pList=null;
		Person p =new Person();
		int  count=0;
		try {
			User user =(User)request.getSession().getAttribute("loginUser");
			if(user!=null){
				
				if(user.getRoleId()==2){
				 list = equipmentService.list(equipment);
				 count = equipmentService.count(equipment);
				 pList = personService.lists(p);
				}else{
				equipment.setCreateBy(user.getUid());
				 list = equipmentService.list(equipment);
				 count = equipmentService.count(equipment);
				 p.setCreateBy(user.getUid());
				 pList = personService.lists(p);
				}
				equipmentVo.setList(list);
				equipmentVo.setTotal(count);
				equipmentVo.setPeresonlist(pList);
				return new ReturnResultUtil(200,"查询成功",equipmentVo);
			}else{
				return new ReturnResultUtil(500,"登录失效，请重新登录");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"查询失败",equipmentVo);
		}
		
	}
	@RequestMapping(value="addorupdate",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil addorupdate(HttpServletRequest request,HttpServletResponse response,EquipmentVo equipment){
		equipment.setCreateDate(DateUtil.DateTimeToStr(new Date()));
		equipment.setUpdateDate(DateUtil.DateTimeToStr(new Date()));
		OperationInfo record =new OperationInfo();
		try {
			User u =(User)request.getSession().getAttribute("loginUser");
			if(u != null){
				
			if(equipment.getEid()!=null){
				equipment.setUpdateBy(u.getUid());
				equipment.setEquipmentStatus(0);
				int i = equipmentService.update(equipment);
				if(i>0){
					record.setUid(u.getUid());
					record.setType(3);
					record.setContent(u.getUserName()+"跟换设备");
					record.setUpdateId(equipment.getEid());
					record.setCreateDate(DateUtil.DateTimeToStr(new Date()));
					int j = operationInfoService.insertSelective(record);
					if(j>0){
						return new ReturnResultUtil(200,"修改成功");
					}else{
						return new ReturnResultUtil(200,"修改失败");
					}
					
				}else{  
					return new ReturnResultUtil(200,"修改失败");
				}
			}else{
				equipment.setCreateBy(u.getUid());
				equipment.setUpdateBy(u.getUid());
				equipment.setEquipmentStatus(1);
				equipment.setStatus(0);
				int result= equipmentService.insert(equipment);
				if(result>0){
					record.setUid(u.getUid());
					record.setType(3);
					record.setContent(u.getUserName()+"添加设备");
					record.setUpdateId(equipment.getEid());
					record.setCreateDate(DateUtil.DateTimeToStr(new Date()));
					int j = operationInfoService.insertSelective(record);
					if(j>0){
						Person person = personService.selectByPrimaryKey(equipment.getPid());
						
						String publickey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDcCfd+c2OPZmVcIw1xEsSi95BrrC/"+
					  			"IgGCc4c3qF2wo+HEUzHHx5FuXyhpRbd7gbc3czVwzcuYKUngurt0ADpkLqhIF3rvs2sS5MhUbxs0"
					  			+ "zIFaohg4Fi53LpxooqT+69JyVxbezZhoaFomNDqWnHFtlfHBK4P64hq8jjEdnXAjWxwIDAQAB";
						person.setRemark(publickey);
						person.setPersonPwd(equipment.getUserPwd());
						person.setEid(equipment.getEid());
						int i = personService.updateByPrimaryKeySelective(person);
						if(i>0){
							record.setUid(u.getUid());
							record.setType(2);
							record.setContent(u.getUserName()+"设备绑定人员");
							record.setUpdateId(person.getPid());
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
	public ReturnResultUtil get(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer eid){
		
		try {
			EquipmentVo equipment = equipmentService.get(eid);
		
			return new ReturnResultUtil(200,"查询成功",equipment);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"查询失败");
		}
		
	}
	@RequestMapping(value="del",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil del(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer eid,@RequestParam Integer status){
		OperationInfo record =new OperationInfo();
		String str="";
		try {
			User u =(User)request.getSession().getAttribute("loginUser");
			if(u!=null){
				EquipmentVo equipment = equipmentService.get(eid);
				equipment.setEquipmentStatus(status);
				equipment.setUpdateDate(DateUtil.DateTimeToStr(new Date()));
				 int i = equipmentService.update(equipment);
				 if(i>0){
					 	record.setUid(u.getUid());
						record.setType(3);
						if(status==2){
							str="禁用该设备";
						}else if(status==3){
							str="将该设备状态改为损坏";
						}
						record.setContent(u.getUserName()+str);
						record.setUpdateId(eid);
						record.setCreateDate(DateUtil.DateTimeToStr(new Date()));
						int o = operationInfoService.insertSelective(record);
						if(o>0){
							return new ReturnResultUtil(200,"修改成功");
						}else{
							return new ReturnResultUtil(200,"修改失败");
						}
				 }else{
					 return new ReturnResultUtil(500,"修改失败");
				 }
				
			
			}else{
				return new ReturnResultUtil(500,"登录失效请重新登录");	
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"查询失败");
		}
		
	}
	@RequestMapping(value="change",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil change(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer eid,@RequestParam String  remark){
		OperationInfo record =new OperationInfo();
		try {
			User u =(User)request.getSession().getAttribute("loginUser");
			if(u!=null){
			EquipmentVo equipment = equipmentService.get(eid);
			equipment.setRemark(remark);
			equipment.setStatus(1);
			equipment.setUpdateDate(DateUtil.DateTimeToStr(new Date()));
			 int i = equipmentService.update(equipment);
			 if(i>0){
					record.setUid(u.getUid());
					record.setType(3);
					record.setContent(u.getUserName()+"申请跟换设备");
					record.setUpdateId(eid);
					record.setCreateDate(DateUtil.DateTimeToStr(new Date()));
					int o = operationInfoService.insertSelective(record);
					if(o>0){
						return new ReturnResultUtil(200,"修改成功");
					}else{
						return new ReturnResultUtil(200,"修改失败");
					}
			 }else{
				 return new ReturnResultUtil(500,"修改失败");
			 }
			}else{
				return new ReturnResultUtil(500,"登录失效请重新登录");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"查询失败");
		}
		
	}
	@RequestMapping(value="examine",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil examine(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer eid){
		OperationInfo record =new OperationInfo();
		try {
			User u =(User)request.getSession().getAttribute("loginUser");
			if(u!=null){
			EquipmentVo equipment = equipmentService.get(eid);
			equipment.setStatus(2);
			equipment.setUpdateDate(DateUtil.DateTimeToStr(new Date()));
			 int i = equipmentService.update(equipment);
			 if(i>0){
				 	record.setUid(u.getUid());
					record.setType(3);
					record.setContent(u.getUserName()+"审核申请跟换设备，审核成功");
					record.setUpdateId(eid);
					record.setCreateDate(DateUtil.DateTimeToStr(new Date()));
					int o = operationInfoService.insertSelective(record);
					if(o>0){
						return new ReturnResultUtil(200,"修改成功");
					}else{
						return new ReturnResultUtil(200,"修改失败");
					}
			 }else{
				 return new ReturnResultUtil(500,"修改失败");
			 }
			}else{
				return new ReturnResultUtil(500,"登录失效请重新登录");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"查询失败");
		}
		
	}
	@RequestMapping(value="policeNo",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil policeNo(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer pid){
		
		try {
			String policeNo = personService.policeNo(pid);
			return new ReturnResultUtil(200,"查询成功",policeNo);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"查询失败");
		}
		
	}
	
}
