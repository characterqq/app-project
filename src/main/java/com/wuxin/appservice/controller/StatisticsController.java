package com.wuxin.appservice.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuxin.appservice.model.Dept;
import com.wuxin.appservice.model.Equipment;
import com.wuxin.appservice.model.User;
import com.wuxin.appservice.service.DeptService;
import com.wuxin.appservice.service.EquipmentService;
import com.wuxin.appservice.service.PersonService;
import com.wuxin.appservice.util.ReturnResultUtil;
import com.wuxin.appservice.vo.enums.EquipmentVo;
import com.wuxin.appservice.vo.enums.PersonVo;

/**
 * 数据统计的控制器
 * 
 * @author hn
 *
 */
@Controller
@RequestMapping("statistics")
public class StatisticsController {
	@Autowired
	EquipmentService equipmentService;
	@Autowired
	private DeptService deptService;

	@Autowired
	PersonService personService;

	/**
	 * 进入设备统计页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author hn
	 * @Data 2017/7/19
	 */
	@RequestMapping(value = "device", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String device(HttpServletRequest request, HttpServletResponse response) {
		return "/page/statistics/device";
	}

	/***
	 * 统计系统设备总数，禁用设备占总设备的比例。饼图
	 * 
	 * @param request
	 * @param business
	 *            查询条件
	 * @return
	 */
	@RequestMapping(value = "devicedata", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil devicedata(HttpServletRequest request, Equipment e) {

		User user = (User) request.getSession().getAttribute("loginUser");
		if (user != null) {
			List<EquipmentVo> list = equipmentService.forbiddenDevic(e);

			StringBuffer sbf = new StringBuffer();
			String[] dataType = { "在线", "离线", "禁用", "损坏" };

			sbf.append("[");
			// 将bslist拼接成json格式的字符串，G2画图需要json格式的数据
			for (Integer i = 0; i < dataType.length; i++) {
				for (EquipmentVo eq : list) {
					if (eq.getEquipmentStatus() == i) {
						sbf.append("{name:'" + dataType[i] + "', value:" + eq.getCount() + "},");

					}

				}
			}

			sbf.deleteCharAt(sbf.length() - 1);
			sbf.append("]");
			System.out.println(sbf.toString());
			return new ReturnResultUtil(200, "查询成功", sbf.toString());
		} else {
			return new ReturnResultUtil(500, "登录失效，请重新登录");
		}

	}

	/***
	 * 一天24小时，统计今天每个小时各部门在线人数。可以选择查看过去某一天24小时部门在线人数
	 * 
	 * @param request
	 * @param business
	 *            查询条件
	 * @return
	 */
	@RequestMapping(value = "onLineDevice", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil onLineDevice(HttpServletRequest request, EquipmentVo e) {

		Date d = new Date();

		Calendar c = Calendar.getInstance();
		// 当前的day_of_month加一就是明天时间
		c.add(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr = sdf.format(d);
		String tomorrow = sdf.format(c.getTime());
		User user = (User) request.getSession().getAttribute("loginUser");
		if (user != null) {
			e.setStarDate(dateNowStr);
			e.setEndDate(tomorrow);
			List<EquipmentVo> onLineDevice = equipmentService.onLineDeviceTime(e);

			StringBuffer sbf = new StringBuffer();
			sbf.append("[");
			// 将bslist拼接成json格式的字符串，G2画图需要json格式的数据
			for (int j = 1; j < 25; j++) {
				if (onLineDevice.size() != 0) {

					for (EquipmentVo eq : onLineDevice) {

						if (j == eq.getHour()) {
							sbf.append("{name:'" + eq.getHour() + "', value:" + eq.getCount() + "},");
							break;
						} else {
							sbf.append("{name:'" + j + "', value:" + 0 + "},");
						}
					}

				} else {
					sbf.append("{name:'" + j + "', value:" + 0 + "},");
				}
			}
			sbf.deleteCharAt(sbf.length() - 1);
			sbf.append("]");

			return new ReturnResultUtil(200, "查询成功", sbf.toString());
		} else {
			return new ReturnResultUtil(500, "登录失效，请重新登录");
		}

	}

	/**
	 * 进入人员统计页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author hn
	 * @Data 2017/7/19
	 */
	@RequestMapping(value = "person", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String page(HttpServletRequest request, HttpServletResponse response) {
		return "/page/statistics/person";
	}

	/***
	 * 统计系统人员总数，禁用人员占总设备的比例。画饼图
	 * 
	 * @param request
	 * @param business
	 *            查询条件
	 * @return
	 */
	@RequestMapping(value = "persondata", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil persondata(HttpServletRequest request, PersonVo pv) {

		User user = (User) request.getSession().getAttribute("loginUser");
		if (user != null) {
			List<PersonVo> list = personService.forbiddenPerson(pv);

			StringBuffer sbf = new StringBuffer();
			String[] dataType = { "在线", "禁用" };

			sbf.append("[");
			// 将bslist拼接成json格式的字符串，G2画图需要json格式的数据
			for (PersonVo eq : list) {
				for (Integer i = 0; i < dataType.length; i++) {
					if (eq.getStatus() == i) {
						sbf.append("{name:'" + dataType[i] + "', value:" + eq.getCount() + "},");
					}
				}
			}

			sbf.deleteCharAt(sbf.length() - 1);
			sbf.append("]");
			System.out.println(sbf.toString());
			return new ReturnResultUtil(200, "查询成功", sbf.toString());
		} else {
			return new ReturnResultUtil(500, "登录失效，请重新登录");
		}

	}

	/***
	 * 统计当前时间点和过去时间段内部门在线人数。
	 * 
	 * @param request
	 * @param business
	 *            查询条件
	 * @return
	 */
	@RequestMapping(value = "onLinePerson", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil onLinePerson(HttpServletRequest request, PersonVo pv) {
		Date d = new Date();
		Calendar c = Calendar.getInstance();
		// 当前的day_of_month加一就是明天时间
		c.add(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr = sdf.format(d);
		String tomorrow = sdf.format(c.getTime());
		if (pv.getStarDate() == null || "".equals(pv.getStarDate()) || pv.getEndDate() == null
				|| "".equals(pv.getEndDate())) {
			pv.setStarDate(dateNowStr);
			pv.setEndDate(tomorrow);
		}
		User user = (User) request.getSession().getAttribute("loginUser");
		if (user != null) {
			List<PersonVo> list = personService.onLinePerson(pv);
			List<Dept> selectDept = deptService.selectDept();
			StringBuffer sbf = new StringBuffer();

			sbf.append("[");
			for (Dept dept : selectDept) {
				// 将bslist拼接成json格式的字符串，G2画图需要json格式的数据
				if (list.size() != 0) {
					for (PersonVo eq : list) {
						if (dept.getDid() == eq.getDid()) {
							sbf.append("{name:'" + eq.getDname() + "', ");
							if (eq.getCount() != null) {
								sbf.append("value:" + eq.getCount() + "},	");
								break;
							} else {
								sbf.append("value:" + 0 + "},	");
							}

						}

					}
				} else {
					sbf.append("{name:'" + dept.getDname() + "', value:" + 0 + "},");
				}
			}

			sbf.deleteCharAt(sbf.length() - 1);
			sbf.append("]");
			System.out.println(sbf.toString());
			return new ReturnResultUtil(200, "查询成功", sbf.toString());
		} else {
			return new ReturnResultUtil(500, "登录失效，请重新登录");
		}

	}

	/***
	 * 统计当前时间点和过去时间 段内部门在线人数。
	 * 
	 * @param request
	 * @param business
	 *            查询条件
	 * @return
	 */
	@RequestMapping(value = "onLinePersonTime", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil onLinePersonTime(HttpServletRequest request, PersonVo pv) {
		Date d = new Date();

		Calendar c = Calendar.getInstance();
		// 当前的day_of_month加一就是明天时间
		c.add(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr = sdf.format(d);
		String tomorrow = sdf.format(c.getTime());
		if (pv.getStarDate() == null || "".equals(pv.getStarDate()) || pv.getEndDate() == null
				|| "".equals(pv.getEndDate())) {
			pv.setStarDate(dateNowStr);
			pv.setEndDate(tomorrow);
		}
		User user = (User) request.getSession().getAttribute("loginUser");
		if (user != null) {
			List<PersonVo> onLinePersonTime = personService.onLinePersonTime(pv);
			List<Map<Integer, Integer>> listMap = new ArrayList<>();
			List<String> listTime = new ArrayList<>();
			List<Dept> selectDept = deptService.selectDept();
			Map<Integer, Integer> mapstr = new HashMap<>();
			if (onLinePersonTime.size() != 0) {
				for (PersonVo p : onLinePersonTime) {
					for (int j = 1; j < 25; j++) {
						if (j == p.getHour()) {
							mapstr.put(p.getHour(), p.getCount());
							
						} else {
							mapstr.put(j, 0);

						}

					}

					listTime.add(p.getDname());

				}
			} else {

				for (Dept dept : selectDept) {

					for (int j = 1; j < 25; j++) {

						mapstr.put(j, 0);
						
					}
					listTime.add(dept.getDname());
					listMap.add(mapstr);
				}

			}

			String sbf = "";

			sbf += "[";
			for (int j = 1; j < 25; j++) {
				sbf += "{\"date\":\"" + j + "\",";
				int x = 0;
				for (Map<Integer, Integer> m : listMap) {
					// if(x==m.keySet().size()-1){
					// sbf.append("\""+listTime.get(x).substring(5)+"\":"+
					// m.get(keys[j]));//不取年份
					// }else{
					System.out.println(listTime.get(x));
					sbf += "\"" + listTime.get(x) + "\":" + m.get(j) + ",";
					// }
					x++;
				}
				sbf = sbf.substring(0, sbf.length() - 1);
				if (j == 25 - 1) {
					sbf += "}";
				} else {
					sbf += "},";
				}

			}
			sbf += "]";

			System.out.println(sbf.toString());
			return new ReturnResultUtil(200, "查询成功", sbf.toString());
		} else {
			return new ReturnResultUtil(500, "登录失效，请重新登录");
		}

	}
}
