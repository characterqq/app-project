package com.wuxin.appservice.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wuxin.appservice.model.Equipment;
import com.wuxin.appservice.model.Heartbeat;
import com.wuxin.appservice.model.Logindata;
import com.wuxin.appservice.model.Person;
import com.wuxin.appservice.service.EquipmentService;
import com.wuxin.appservice.service.HeartbeatService;
import com.wuxin.appservice.service.LogindataService;
import com.wuxin.appservice.service.PersonService;
import com.wuxin.appservice.service.UserService;
import com.wuxin.appservice.util.DateUtil;
import com.wuxin.appservice.util.IPSeeker;
import com.wuxin.appservice.util.IPUtils;
import com.wuxin.appservice.util.ReturnResultUtil;
import com.wuxin.appservice.util.rsa.RSAUtils;
import com.wuxin.appservice.vo.enums.EquipmentVo;
import com.wuxin.appservice.vo.enums.IntefaceReturnBO;
import com.wuxin.appservice.vo.enums.InterfaceResultVo;
import com.wuxin.appservice.vo.enums.UserVo;

import interfacedemo.MD5Util;


/**
 * 安全验证的控制器
 * @author hn
 *
 */
@Controller
@RequestMapping("deviceLogin")
public class DeviceLoginController {
	@Autowired
	EquipmentService equipmentService;
	@Autowired
	UserService userService;
	@Autowired
	private PersonService personService;
	@Autowired
	LogindataService logindataService;
	@Autowired
	HeartbeatService heartbeatService;
	//ip
	public static IPSeeker seeker = new IPSeeker();
	private Logger log = Logger.getLogger(DeviceLoginController.class);
	
	
	/**
	 * <strong>App端第一次登录或缓存数据被清空后登录</strong>
	 * <p>
	 * 	app提交  md5（警号+密码+时间戳）被md5加密后的值, 服务端接收验证通过后返回token给</br>
	 * 	客户端，并把token保存到session中去。
	 * </p>
	 * @param request
	 * @param response
	 * @param sign md5加密后的字符串
	 * @return 
	 */
	@RequestMapping(value="initLogon",method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public ReturnResultUtil initLogon(HttpServletRequest request,String policeNo,String personPwd,String timeStamp,String equipment, 
			String sign) {
		try {
			if (sign==null||"".equals(sign)||policeNo == null||personPwd==null||timeStamp==null||equipment==null) {
				return new ReturnResultUtil(500, "请传入参数");
			} else {
				//1、先验签
				String verifyParamStr = "policeNo="+policeNo+"&personPwd="+personPwd+"&timeStamp="+timeStamp;
				if (MD5Util.verify(verifyParamStr, sign)) {
					log.info("验签通过");
				}else {
					log.info("验签失败");
					return new ReturnResultUtil(500, "请求异常");
				}
				//2、计算时间戳是否过期
				Long submitTime = new Long(timeStamp);
				Long currentTime = new Date().getTime();
				Long space = currentTime - submitTime;
				if ((space/1000/60)>2) {	//	
					return new ReturnResultUtil(500,"请求异常");
				}
				Person person = new Person();
				person.setPoliceNo(new Integer(policeNo));
				person.setPersonPwd(personPwd);
				Person loginPerson = personService.loginEquipment(person);
				if (loginPerson == null) {
					log.info("用户不存在");
					return new ReturnResultUtil(500, "警号或密码错误！");
				}
				if (loginPerson.getStatus() == Person.PERSON_STATUS_FORBIT) {
					log.info("用户被禁用");
					return new ReturnResultUtil(500, "账号被禁用,请联系管理员！");
				}
				//警员第一次登录成功,绑定设备
				//如果是在客户端注销账户后重新登录时需要验证设备号是否一致，如果不一致表示警员没有使用自己的设备登录，这是不被允许的。
				//查找该警员名下所有可用的并且是和客户端传上来的设备id相同的设备。
				Equipment mEquipment = JSONObject.parseObject(equipment, Equipment.class);
				if (mEquipment!=null && loginPerson.getPid()!=null) {
					mEquipment.setPid(loginPerson.getPid());
					Equipment checkResult = equipmentService.checkValiableEquipment(mEquipment);
					if (checkResult!=null) {
						//警员当前要登录的设备id号必须要和库里的设备号相同,如果不同则表示该警员的账号在其他设备登录,这是不被允许的。
						if (checkResult.getEquipmentNo()!=null && checkResult.getEquipmentNo().equals(mEquipment.getEquipmentNo())) {
							if (checkResult.getStatus()==0||checkResult.getStatus()==1) {	//该用户有自己的设备,并且设备有效
								//根据当前时间生成token
								String token = MD5Util.sign(currentTime.toString());
								//将token作为key, 警员id作为值存入session, 用作后期登录、查询验证。客户端拿到token会存在本地。
								HttpSession session = request.getSession();
								session.setAttribute(token, person.getPid());
								log.info("登录成功");
								return new ReturnResultUtil(200, "登录成功", token);
							}else if (checkResult.getStatus()==3) {		//该用户设备被禁用
								log.info("您的设备已被禁用,请联系管理员!");
								return new ReturnResultUtil(500, "您的设备已被禁用,请联系管理员!");
							}else if (checkResult.getStatus()==4) {		//设备已损毁
								log.info("您的设备已报损,请联系管理员修改！");
								return new ReturnResultUtil(500, "您的设备已报损,请联系管理员修改！");
							}
						}else {
							log.info("这不是您的设备,不能进行登录操作");
							return new ReturnResultUtil(500, "这不是您的设备,不能进行登录操作");
						}
					}else{	//表示该用户名下没有设备,该用户是新用户,则添加该设备
						int insertResult = equipmentService.insertSelective(mEquipment);
						if (insertResult>0) {
							//根据当前时间生成token
							String token = MD5Util.sign(currentTime.toString());
							//将token作为key, 警员id作为值存入session, 用作后期登录、查询验证。客户端拿到token会存在本地。
							HttpSession session = request.getSession();
							session.setAttribute(token, person.getPid());
							log.info("登录成功");
							return new ReturnResultUtil(200, "登录成功", token);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("抛出异常"+e.getMessage());
			return new ReturnResultUtil(500, "请求异常");
		}
		log.info("登录失败");
		return new ReturnResultUtil(500, "请求异常");
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param requParame
	 * @return
	 */
	@RequestMapping(value = "verification", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String logon(HttpServletRequest request, HttpServletResponse response, String requParame) {
		InterfaceResultVo resultVo = new InterfaceResultVo();

		try {
			if (requParame == null || "".equals(requParame)) {
				resultVo.setSuccess(false);
				resultVo.setMessage("请求参数不能为空");
				return JSONObject.toJSONString(resultVo);
			} else {
				Map<String, String> map = RSAUtils.getSignandNoSignStr(RSAUtils.decrypt(requParame));// 获得签名和没有签名的字符串
				String oldSign = map.get("sign");// 原签
				String noSignStr = map.get("noSignStr");// 去除签名的字符串
				String newSign = MD5Util.MD5Encode(noSignStr, "utf-8");// 现在的签名
				if (oldSign.equals(newSign)) {
					Map<String, String> mapParame = RSAUtils.getparameMap(noSignStr);
					Person person = new Person();
					person.setPersonPwd(mapParame.get("personPwd"));
					person.setPoliceNo(Integer.parseInt(mapParame.get("policeNo")));
					Person perr = personService.loginEquipment(person);
					if (perr == null) {// 如果不存在改用户
						resultVo.setSuccess(false);
						resultVo.setMessage("您的账号或密码不正确！");
						return JSONObject.toJSONString(resultVo);
					} else {
						resultVo.setSuccess(true);
						resultVo.setMessage("验签成功");
						resultVo.setPerson(perr);
						String resultJson = RSAUtils.encrypt(JSONObject.toJSONString(resultVo));
						String sign = MD5Util.MD5Encode(JSONObject.toJSONString(resultVo), "utf-8");// md5签名
						IntefaceReturnBO returnStr = new IntefaceReturnBO();
						returnStr.setReturnJson(resultJson);
						returnStr.setSign(sign);
						return RSAUtils.encrypt(JSONObject.toJSONString(returnStr));
					}
				} else {
					resultVo.setSuccess(false);
					resultVo.setMessage("验签失败");
					return JSONObject.toJSONString(resultVo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setMessage("验签失败");
			return JSONObject.toJSONString(resultVo);
		}

	}

/**
 * 设备绑定接口
 * @param request
 * @param response
 * @param ev
 * @return
 */
@RequestMapping(value="binDingEquipment",method=RequestMethod.POST,produces="application/json;charset=utf-8")
@ResponseBody
public ReturnResultUtil binDingEquipment(HttpServletRequest request,HttpServletResponse response,EquipmentVo ev){
		try {
			if(ev==null || "".equals(ev)){
				return new ReturnResultUtil(500,"请传入参数");
			}else{
				EquipmentVo eVo = equipmentService.binDingEquipment(ev);
				
				if(eVo!=null){
					//判断用户是否被禁用
					if(eVo.getStatus()==1){
						return new ReturnResultUtil(500,"用户已被禁用");
					}else{
						eVo.setEquipmentStatus(0);
						//通过设备id去修改设备状态
						int i = equipmentService.binDing(eVo);
						if(i!=0){
							return new ReturnResultUtil(200,"已成功绑定",eVo);
						}else{
							return new ReturnResultUtil(500,"绑定失败");
						}
					}
					
				}else{
					Person person=new Person();
					person.setPersonPwd(ev.getPersonPwd());
					person.setPoliceNo(ev.getPoliceNo());
					//通过员工警号和密码查询员工pid
					Person pe = personService.loginEquipment(person);
					if(pe.getStatus()==1){
						return new ReturnResultUtil(500,"用户已被禁用");
					}else{
						ev.setPid(pe.getPid());
						ev.setEquipmentStatus(0);
						//通过设备id去修改pid与设备状态
						int i = equipmentService.binDing(ev);
						if(i!=0){
							return new ReturnResultUtil(200,"绑定成功");
						}else{
							return new ReturnResultUtil(500,"绑定失败");
						}
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil(500,"请求异常");
		}
		
	}
/**
 * 设备登录接口
 * @param request
 * @param response
 * @param requParame
 * @return
 * @throws Exception 	 
 */
@RequestMapping(value="loginEquipment",method=RequestMethod.POST,produces="application/json;charset=utf-8")
@ResponseBody
public Map<String,String> loginEquipment(HttpServletRequest request,HttpServletResponse response,Person person) throws Exception{
		 Map<String,String> map = new HashMap<>();
		if(person==null){
			map.put("message", "请求参数不能为空");
			return map;
			}else{
				 Person per = personService.loginEquipment(person);
				 if(per==null){
					map.put("message", "账号或密码不正确");
				}else{
					 TreeMap<String, String> paramsMap = new TreeMap<>();
					 paramsMap.put("personPwd", person.getPersonPwd());
					 paramsMap.put("policeNo", person.getPoliceNo().toString());
					 //拼接请求的参数
					 String paramsNoSign= getUrlParamSrc(paramsMap);
					 //签名
					 String sign = MD5Util.MD5Encode(paramsNoSign,"utf-8");
					 //请求参数加上签名
					 paramsNoSign=getUrlParamSrc(paramsMap)+"&sign="+sign;
					 //对有签名的参数进行加密
					 String paramsSign=RSAUtils.encrypt(paramsNoSign);
					//对返回的结果进行解密获得json格式的字符串
					 String destr= RSAUtils.decrypt(paramsSign);
					//将json格式的字符串转换成map集合 以属性为key 值为value
					Map<String, String> mapp = RSAUtils.getparameMap(destr);
					String oldSign=mapp.get("sign").toString();
					//String returnJson = mapp.get("paramsSign").toString();
					//拼接请求的参数
					String paramsNoSigns= getUrlParamSrc(paramsMap);
					//解密不包含签名的字符串
					//String tempJson = RSAUtils.decrypt(paramsNoSigns);
					//map.put("result", tempJson);
					//对解密后的字符串进行签名
					sign = MD5Util.MD5Encode(paramsNoSigns,"utf-8");
					//验签
					if(sign.equals(oldSign)){
						if(per.getStatus()==1){
							map.put("message", "用户已被禁用");
						}else{
							EquipmentVo ev = new EquipmentVo();
							ev.setPid(per.getPid());
							//设备状态
							ev.setEquipmentStatus(0);
							//得到设备id
							ev.setEid(per.getEid());
							//通过设备id去修改设备状态
							int i = equipmentService.binDing(ev);
							if(i==0){
								map.put("message", "登录失败");
							}else{
								String ip = IPUtils.getIpAddr(request);
								String ipLocation = seeker.getAddress(ip);
								Logindata logindata=new Logindata();
								//登录人编号
				    			logindata.setUid(per.getPid());
				    			//设备id
				    			logindata.setEid(per.getEid());
				    			//登录ip
				    			logindata.setLoginIp(ip);
				    			//登录地点
				    			logindata.setLoginPlcae(ipLocation);
				    			//登录人身份
				    			logindata.setRid(3);
				    			//备注
				    			logindata.setRemark("警员登录");
				    			//登录时间
				    			logindata.setLoginDate(DateUtil.DateTimeToStr(new Date()));
				    			int insert = logindataService.insert(logindata);
				    			if(insert==0){
				    				 map.put("message", "登录失败");
				    			}else{
				    				//返回加密的账号密码
									 map.put("paramsSign", paramsSign);
									 map.put("message", "登录成功");
				    			}
							}
						}
					}else{
						map.put("message", "登录失败");
					}
				}
			}
		return map;
	}
/**
 * 设备查询、提交数据身份验证接口 
 * @param request
 * @param response
 * @param requParame
 * @return
 */
@RequestMapping(value="submitVerify",method=RequestMethod.POST,produces="application/json;charset=utf-8")
@ResponseBody
public String submitVerify(HttpServletRequest request,HttpServletResponse response,String requParame){
			InterfaceResultVo resultVo = new InterfaceResultVo();
			try {
				if(requParame==null||"".equals(requParame)){
				resultVo.setSuccess(false);
				resultVo.setMessage("请求参数不能为空");
				return JSONObject.toJSONString(resultVo);
				}else{
				   Map<String,String> map =  RSAUtils.getSignandNoSignStr(RSAUtils.decrypt(requParame));//获得签名和没有签名的字符串
			        String oldSign = map.get("sign");//原签
			        String noSignStr = map.get("noSignStr");//去除签名的字符串
			        String newSign =MD5Util.MD5Encode(noSignStr, "utf-8");//现在的签名
			        if(oldSign.equals(newSign)){
			        	Map<String,String> mapParame =  RSAUtils.getparameMap(noSignStr);
			        	Person person = new Person();
			        	person .setPersonPwd(mapParame.get("personPwd"));
			        	String policeNo = mapParame.get("policeNo");
			        	person.setPoliceNo(Integer.parseInt(policeNo));
			        	Person per = personService.loginEquipment(person);
			        	if (per == null ) {// 如果不存在该用户
			        		resultVo.setSuccess(false);
			    			resultVo.setMessage("您的账号或密码不正确！");
			        		return JSONObject.toJSONString(resultVo);
			    		}else{
			    			resultVo.setSuccess(true);
			    			resultVo.setMessage("验签成功");
			    			resultVo.setPerson(per);
			    				String resultJson =RSAUtils.encrypt(JSONObject.toJSONString(resultVo));
			    				String sign = MD5Util.MD5Encode(JSONObject.toJSONString(resultVo), "utf-8");//md5签名
			    				IntefaceReturnBO returnStr = new IntefaceReturnBO();
			    				returnStr.setReturnJson(resultJson);
			    				returnStr.setSign(sign);
			    				return RSAUtils.encrypt(JSONObject.toJSONString(returnStr));
			    		}
			        }else{
			        	resultVo.setSuccess(false);
			        	resultVo.setMessage("验签失败");
			        	return JSONObject.toJSONString(resultVo);
			        }
				}
			} catch (Exception e) {
				e.printStackTrace();
				resultVo.setMessage("验签失败");
				return JSONObject.toJSONString(resultVo);
			}
			
		}
/**
 * 设备心跳接口 
 * @param request
 * @param response
 * @param eid
 * @return
 * @throws Exception 
 */
@RequestMapping(value="heartbeatInterfaces",method=RequestMethod.POST,produces="application/json;charset=utf-8")
@ResponseBody
public ReturnResultUtil heartbeatInterfaces(HttpServletRequest request,HttpServletResponse response,Integer eid) throws Exception{
	Heartbeat heartbeat = new Heartbeat();
	//设备在线时将设备id保存至session中
	HttpSession session=request.getSession();
	session.setAttribute("eid", eid);
	EquipmentVo evo = new EquipmentVo();
	if(eid==null){
		//设备状态:离线
		evo.setEquipmentStatus(1);
		Integer eids = (Integer)session.getAttribute("eid");
		//得到设备id
		evo.setEid(eids);
		//通过设备id去修改设备状态
		int i = equipmentService.binDing(evo);
		if(i!=0){
			return new ReturnResultUtil(500,"已离线");
		}else{
			return new ReturnResultUtil(500,"不能为空");
		}
	}else{
		//通过设备id查询该设备
		EquipmentVo ev= equipmentService.selectByPrimaryKey(eid);
		if(ev==null){
			return new ReturnResultUtil(500,"查询失败");
		}else{
			//设备状态:在线
			ev.setEquipmentStatus(0);
			//通过设备id去修改设备状态
			int i = equipmentService.binDing(ev);
			if(i!=0){
				String ip = IPUtils.getIpAddr(request);
				String ipLocation = seeker.getAddress(ip);
				//人员id
				heartbeat.setPid(ev.getPid());
				//设备id
				heartbeat.setEid(ev.getEid());
				//警号
				heartbeat.setPoliceNo(ev.getPoliceNo());
				//ip
				heartbeat.setIp(ip);
				//所在经纬度
				heartbeat.setJwd("5");
				//当前所在地
				heartbeat.setAddress(ipLocation);
				//当前时间
				heartbeat.setNowtime(DateUtil.DateTimeToStr(new Date()));
				//备注
				heartbeat.setRemark(ev.getRemark());
				//创建人
				heartbeat.setCreateBy(ev.getCreateBy());
				//修改人
				heartbeat.setUpdateBy(ev.getUpdateBy());
				int j = heartbeatService.insertSelective(heartbeat);
				if(j!=0){
					return new ReturnResultUtil(200,"查询成功");
				}else{
					return new ReturnResultUtil(500,"查询失败");
				}
			}else{
				return new ReturnResultUtil(500,"查询失败");
			}

		}
	}
}

/**
 * 将请求的参数拼接成key1=value1&key2=value2的形式的方法
 * @param paramsMap
 * @return
 */
public static String getUrlParamSrc(TreeMap<String, String> paramsMap) {
	StringBuffer paramstr = new StringBuffer();
	for (String pkey : paramsMap.keySet()) {
		String pvalue = paramsMap.get(pkey);
		if (null != pvalue && "" != pvalue) {// 空值不传递，不签名
			paramstr.append(pkey + "=" + pvalue + "&"); // 签名原串，不url编码
		}
		}
		// 去掉最后一个&
		String result = paramstr.substring(0, paramstr.length() - 1);
		return result;
	}
}	

