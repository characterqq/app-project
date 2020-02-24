package com.wuxin.appservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 部门管理的控制器
 * @author hn
 *
 */
@Controller
@RequestMapping("index")
public class IndexController {


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
		return "page/index";
	}
	
}
