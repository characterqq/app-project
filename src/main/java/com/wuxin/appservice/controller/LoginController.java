package com.wuxin.appservice.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuxin.appservice.model.Logindata;
import com.wuxin.appservice.model.User;
import com.wuxin.appservice.service.LogindataService;
import com.wuxin.appservice.service.UserService;
import com.wuxin.appservice.util.DateUtil;
import com.wuxin.appservice.util.IPSeeker;
import com.wuxin.appservice.util.IPUtils;
import com.wuxin.appservice.util.ReturnResultUtil;
import com.wuxin.appservice.vo.enums.UserVo;

/**
 * 用户登录管理的控制器
 * @author hk
 *
 */
@Controller
@RequestMapping("login")
public class LoginController {
	/** 日志打印*/
	protected Logger logger = Logger.getLogger(getClass());
	@Autowired
	UserService userService;
	@Autowired
	LogindataService logindataService;

	public static IPSeeker seeker = new IPSeeker();
/**
 * 验证登录是否成功
 * @param request
 * @param response
 * @param uv
 * @return
 */
@ResponseBody
@RequestMapping("login")
public ReturnResultUtil login(HttpServletRequest request,HttpServletResponse response,UserVo uv) {
	Logindata logindata = new Logindata();
	try {
		UserVo users = userService.loginUser(uv);
		//判断是否登录成功 不为空
		if(users!=null){
			//判断是否被禁用
			String ip = IPUtils.getIpAddr(request);
			String ipLocation = seeker.getAddress(ip);
			if(users.getStatus()!=1){
	    			//登录人编号
	    			logindata.setUid(users.getUid());
	    			//设备id
	    			logindata.setEid(1);
	    			logindata.setLoginIp(ip);
	    			logindata.setLoginPlcae(ipLocation);
	    			logindata.setEid(users.getEid());
	    			//登录人身份
	    			logindata.setRid(users.getRoleId());
	    			//登录时间
	    			logindata.setLoginDate(DateUtil.DateTimeToStr(new Date()));
	    			int insert = logindataService.insert(logindata);
	    			if(insert>0){
	    				HttpSession session=request.getSession();
	    	       		 session.setAttribute("loginUser", users);
	    				return new ReturnResultUtil(200,"登录成功",users);
	    			}else{
	    				return new ReturnResultUtil(500,"登录失败");
	    			}
	    			
	    		
			}else{
				return new ReturnResultUtil(500,"用户已被禁用");
			}
			
		}else{
			return new ReturnResultUtil(500,"用户名或密码不正确，请重试");
		}
	} catch (Exception e) {
		e.printStackTrace();
		return new ReturnResultUtil(500,"登录异常");
	}
	
		
	}
/**
 * 修改用户密码
 * @param request
 * @param response
 * @param uv
 * @return
 */
	@ResponseBody
	@RequestMapping("updatePassword")
	public ReturnResultUtil updatePassword(HttpServletRequest request,HttpServletResponse response,UserVo uv){

		UserVo userVo =new UserVo();
		userVo.setUserPwd(uv.getUserPwd());
		userVo.setOldPassword(uv.getOldPassword());
		userVo.setUpdateDates(DateUtil.DateTimeToStr(new Date()));
		int i = userService.updateUserPwd(userVo);
		if(i!=0){
			return new ReturnResultUtil(200,"修改成功",userVo);
		}else{
			return new ReturnResultUtil(500,"修改失败");
		}
		
	}
	/**
	 * 退出登录
	 * @param request
	 * @return
	 */
	@RequestMapping("logout")
	@ResponseBody
	public ReturnResultUtil logout(HttpServletRequest request){
	HttpSession session=request.getSession();
		 if(session.getAttribute("loginUser") !=null){
			session.removeAttribute("loginUser");
		 }
		 User user =(User)request.getSession().getAttribute("loginUser");
		return new ReturnResultUtil(200,"退出成功");
			
	}
	/**
	 * 进入登录页面
	 * @param request
	 * @param response
	 * @return
	 * @author hn
	 * @Data 2017/7/19
	 */
	@RequestMapping(value="loginPage",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	public String loginPage(HttpServletRequest request,HttpServletResponse response){
		return "page/login";
	}
	
}

