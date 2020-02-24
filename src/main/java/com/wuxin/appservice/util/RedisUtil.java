package com.wuxin.appservice.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.wuxin.appservice.model.User;
import com.wuxin.appservice.vo.enums.UserVo;

import redis.clients.jedis.Jedis;

//import redis.clients.jedis.Jedis;

/**
 * redis工具类
 * 
 * @author hk
 *
 */
public class RedisUtil {
	private static Jedis jedis = null;
	private static String RedisUtiluid=null;
	/**
	 * 
	 * setString: TODO
	 * 
	 * @param @param
	 *            key
	 * @param @param
	 *            value
	 * @param @param
	 *            seconds 过期时间，单位秒
	 * @return void
	 * @throws @since
	 *             hk Ver 1.0
	 */
	public static void setString(String key, String value, int seconds) {
	
		try {
			jedis =RedisPool.getJedis();
			jedis.set(key, value);
			jedis.expire(key, seconds);
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public static String getString(String key) {
		try {
			jedis = RedisPool.getJedis();
			String value = jedis.get(key);
			return value;
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return "";
	}

	/**
	 * 得到cookie里面的值 getCookieValue: TODO
	 * 
	 * @param @param
	 *            key
	 * @param @param
	 *            request
	 * @param @return
	 * @return String
	 * @throws @since
	 *             hk Ver 1.0
	 */
	public static String getCookieValue(String key, HttpServletRequest request) {
		try{
		Cookie[] cookies = request.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals(key)) {
					String uid = cookie.getValue();
					return uid;
				}
			}
		}catch(Exception e){
		}
		return null;
	}

	public static void setCookie(String key, String value, int seconds, HttpServletResponse response) {
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(seconds);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 使用该方法必须保证cookie中存在uid 通过cookie得到redis中的值 getStringByCookie: TODO
	 * 
	 * @param @param
	 *            key
	 * @param @param
	 *            request
	 * @param @return
	 * @return String
	 * @throws @since
	 *             hk Ver 1.0
	 */
	public static String getStringByuid(String key, HttpServletRequest request) {
		String uid = getCookieValue("uid", request);// 得到cookie中的uid
		if (!StringUtils.isEmpty(uid)) {
			//System.out.println(key + "_" + uid+"--get");
			String obj = getString(key + "_" + uid);
			if (obj == null) {
				obj = "";
			}
			//System.out.println(obj);
			return obj;
		}
		return "";
	}

	/**
	 * 使用该方法必须保证cookie中存在uid setStringByuid: TODO
	 * 
	 * @param @param
	 *            key
	 * @param @param
	 *            value
	 * @param @param
	 *            seconds
	 * @param @param
	 *            request
	 * @return void
	 * @throws @since
	 *             hk Ver 1.0
	 */
	public static void setStringByuid(String key, String value, int seconds, HttpServletRequest request) {
		String uid = getCookieValue("uid", request);// 得到cookie中的uid
		if(StringUtils.isEmpty(uid)){
			uid=RedisUtiluid;
		}
		if (!StringUtils.isEmpty(uid)) {
			System.out.println(key + "_" + uid+"--set");
			setString(key + "_" + uid, value, seconds);
		}
	}

	/**
	 * 使用该方法必须保证cookie中存在uid 通过cookie得到redis中的值 getsetModelByCookie: TODO
	 * 
	 * @param @param
	 *            key
	 * @param @param
	 *            request
	 * @param @return
	 * @return String
	 * @throws @since
	 *             hk Ver 1.0
	 */
	public static Object getModelByuid(String key, HttpServletRequest request) {
		String uid = getCookieValue("uid", request);// 得到cookie中的uid
		if (!StringUtils.isEmpty(uid)) {
			return getModel(key + "_" + uid);
		}
		return null;
	}

	/**
	 * 使用该方法必须保证cookie中存在uid setModelByuid: TODO
	 * 实体类需 implements Serializable
	 * @param @param
	 *            key
	 * @param @param
	 *            value
	 * @param @param
	 *            seconds
	 * @param @param
	 *            request
	 * @return void
	 * @throws @since
	 *             hk Ver 1.0
	 */
	public static void setModelByuid(String key, Object model, int seconds, HttpServletRequest request) {
		String uid = getCookieValue("uid", request);// 得到cookie中的uid
		if(StringUtils.isEmpty(uid)){
			uid=RedisUtiluid;
		}
		if (!StringUtils.isEmpty(uid)) {
			setModel(key + "_" + uid, model, seconds);
		}
	}

	/**
	 * 使用该方法必须保证cookie中存在uid delStringByuid: TODO
	 * 
	 * @param @param
	 *            key
	 * @return void
	 * @throws @since
	 *             hk Ver 1.0
	 */
	public static void delStringByuid(String key, HttpServletRequest request) {
		String uid = getCookieValue("uid", request);// 得到cookie中的uid
		if (!StringUtils.isEmpty(uid)) {
			delString(key + "_" + uid);
		}
	}

	/**
	 * 使用该方法必须保证cookie中存在uid delModelByuid: TODO
	 * 
	 * @param @param
	 *            key
	 * @return void
	 * @throws @since
	 *             hk Ver 1.0
	 */
	public static void delModelByuid(String key, HttpServletRequest request) {
		String uid = getCookieValue("uid", request);// 得到cookie中的uid
		if (!StringUtils.isEmpty(uid)) {
			delModel(key + "_" + uid);
		}
	}

	/**
	 * 
	 * delString: TODO
	 * 
	 * @param @param
	 *            key
	 * @return void
	 * @throws @since
	 *             hk Ver 1.0
	 */
	public static void delString(String key) {
		try {
			jedis = RedisPool.getJedis();
			jedis.del(key);
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 
	 * delModel: TODO
	 * 
	 * @param @param
	 *            key
	 * @return void
	 * @throws @since
	 *             hk Ver 1.0
	 */
	public static void delModel(String key) {
//		try {
//			jedis = RedisPool.getJedis();
//			jedis.del(key.getBytes());
//		} catch (Exception e) {
//			// e.printStackTrace();
//		} finally {
//			if (jedis != null) {
//				jedis.close();
//			}
//		}
	}
	/**
	 * 将用户Id保存到redis saveUserId: TODO
	 * 
	 * @param @param
	 *            user
	 * @param @param
	 *            response
	 * @param @param
	 *            request
	 * @return void
	 * @throws @since
	 *             hk Ver 1.0
	 */
	public static void saveUserId(String uid,int seconds,HttpServletResponse response, HttpServletRequest request) {
		String uId=getCookieValue("uid", request);
		if(StringUtil.isEmpty(uId)){
			RedisUtiluid=UUIDUtil.getUUID();
			
			setCookie("uid", RedisUtiluid,seconds , response);
		}
			setStringByuid("uid", uid, seconds, request);
	}
	
	/**
	 * 将类对象保存到redis setModel: seconds过期时间，单位秒
	 * 
	 * @since hk Ver 1.0
	 */
	public static void setModel(String key, Object obj, int seconds) {
		try {
			jedis = RedisPool.getJedis();
			jedis.set(key.getBytes(), SerializeUtil.serialize(obj));
			jedis.expire(key.getBytes(), seconds);
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 得到类对象 getModel:
	 * 
	 * @since hk Ver 1.0
	 */
	public static Object getModel(String key) {
		try {
			jedis = RedisPool.getJedis();
			Object obj = SerializeUtil.unserialize(jedis.get(key.getBytes()));
			return obj;
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
		}
		}
		return null;
	}

	public static void delCookie(String key, HttpServletResponse response) {
		Cookie cookie = new Cookie(key, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 将用户信息保存到redis saveUser: TODO
	 * 
	 * @param @param
	 *            user
	 * @param @param
	 *            response
	 * @param @param
	 *            request
	 * @return void
	 * @throws @since
	 *             hk Ver 1.0
	 */
	public static void saveUser(UserVo user,int seconds,HttpServletResponse response, HttpServletRequest request) {
		String uId=getCookieValue("uid", request);
		if(StringUtil.isEmpty(uId)){
			RedisUtiluid=UUIDUtil.getUUID();
			setCookie("uid", RedisUtiluid,seconds , response);
		}
			setModelByuid(ConstantsParam.sessionkey_loginUser, user, seconds, request);
			setStringByuid("uid", user.getUid().toString(), seconds, request);
		
	}

	/**
	 * 得到当前登录用户userId
	 * getUserId:
	 * TODO
	 * @param  @param request
	 * @param  @return
	 * @return Integer  
	 * @throws 
	 * @since  hk Ver 1.0
	 */
	public static Integer getUserId(HttpServletRequest request){
		try{
		String uId=RedisUtil.getStringByuid("uid", request);
		if(StringUtil.isNotEmpty(uId)){
			return Integer.parseInt(uId);
		}
		}catch(Exception e){
		}
		return null;
	}
	
	
}