package com.wuxin.appservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuxin.appservice.model.User;
import com.wuxin.appservice.service.LogindataService;
import com.wuxin.appservice.service.OperationInfoService;
import com.wuxin.appservice.util.ReturnResultUtil;
import com.wuxin.appservice.vo.enums.LogVo;
import com.wuxin.appservice.vo.enums.LoginVo;

/**
 * 系统日志控制器
 * @author hk
 *查看系统管理员使用系统的情况。统计管理员登录情况，维护部门、人员、设备等操作。

 */
@Controller
@RequestMapping("log")
public class LogController {
	/** 日志打印*/
	protected Logger logger = Logger.getLogger(getClass());
	@Autowired
	OperationInfoService operationInfoService;
	@Autowired
	LogindataService logindataService;
	/**
	 * 进入操作日志页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="page",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	public String page(HttpServletRequest request,HttpServletResponse response){
		return "/page/log/page";
	}
	/**
	 * 进入登录日志页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="pageLog",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	public String pageLog(HttpServletRequest request,HttpServletResponse response){
		return "/page/log/pageLog";
	}
	/**
	 * 分页查询操作日志所有的方法
	 * @param request
	 * @param response
	 * @param pv
	 * @return
	 */
	@RequestMapping(value="list",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil list(HttpServletRequest request,HttpServletResponse response,LogVo lv){
		LogVo log=new LogVo();
		try {
			//得到session中的当前登录用户
			User user =(User)request.getSession().getAttribute("loginUser");
			//判断用户不为空
			if(user!=null){
				int count = operationInfoService.countAll(lv);
				List<LogVo> list = operationInfoService.selsectAllLog(lv);
				if(list!=null){
					log.setList(list);
					log.setTotal(count);
					if(list!=null){
						return new ReturnResultUtil(200,"查询成功",log);
					}else{
						return new ReturnResultUtil(500,"查询失败",log);
					}
				}else{
					return new ReturnResultUtil(500,"查询失败",log);
				}
				
			}else{
				return new ReturnResultUtil(500,"登录失效请重新登录");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"",log);
		}
	}
	
	/**
	 * 分页查询登录日志所有的方法
	 * @param request
	 * @param response
	 * @param pv
	 * @return
	 */
	@RequestMapping(value="listLog",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil listLog(HttpServletRequest request,HttpServletResponse response,LoginVo lv){
		LoginVo log=new LoginVo();
		try {
			//得到session中的当前登录用户
			User user =(User)request.getSession().getAttribute("loginUser");
			//判断用户不为空
			if(user!=null){
				int count = logindataService.count(lv);
				List<LoginVo> list = logindataService.selsectLog(lv);
				if(list!=null){
					log.setList(list);
					log.setTotal(count);
					if(list!=null){
						return new ReturnResultUtil(200,"查询成功",log);
					}else{
						return new ReturnResultUtil(500,"查询失败",log);
					}
				}else{
					return new ReturnResultUtil(500,"查询失败",log);
				}
				
			}else{
				return new ReturnResultUtil(500,"登录失效请重新登录");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"",log);
		}
	}
}
