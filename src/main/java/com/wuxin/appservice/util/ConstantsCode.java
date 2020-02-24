package com.wuxin.appservice.util;

/**
 * 常量-编码
 * 
 * @author wuxy
 */
public class ConstantsCode {

	/**
	 * 成功
	 */
	public final static String success = "0";

	/**
	 * 传入参数解析错误（格式或加密错误）
	 */
	public final static String param_explain_error = "1001001";

	/**
	 * 传入参数错误（缺少必要字段，类型等错误）
	 */
	public final static String param_content_error = "1001002";

	/**
	 * 密码错误
	 */
	public final static String pwd_error = "1001003";

	/**
	 * 手机号码不存在
	 */
	public final static String mobile_error = "1001004";

	/**
	 * 发送短消息失败
	 */
	public final static String msg_error = "1001005";

	/**
	 * 验证码错误
	 */
	public final static String validCode_code_error = "1001006";

	/**
	 * 手机号码已被其他人绑定
	 */
	public final static String mobile_exists_error = "1001007";

	/**
	 * 不存在的用户
	 */
	public final static String user_notexists_error = "1001008";

	/**
	 * 老密码错误
	 */
	public final static String old_pwd_error = "1001009";

	/**
	 * 无效的token
	 */
	public final static String token_invalid_error = "1001010";

	/**
	 * 系统异常
	 */
	public final static String system_error = "1001111";

	/**
	 * 不支持信用卡
	 */
	public final static String no_credit_card="1001112";
	
	/**
	 * 用户被锁定
	 */
	public final static String user_lock_error = "1001113";

	/**
	 * 不存在的token
	 */
	public final static String token_notexists_error = "1001114";

	/**
	 * 文件超出最大大小
	 */
	public final static String file_max_error = "1001117";

	/**
	 * 已经提交过的分享
	 */
	public final static String share_exists_error = "1001118";

	/**
	 * 请求错误，非文件上传请求
	 */
	public final static String request_uploadFile_error = "1002001";

	/**
	 * 图片不存在
	 */
	public final static String img_notexists_error = "1002002";

	/**
	 * 请求错误
	 */
	public final static String request_error = "1002003";

	/**
	 * 版本需要更新
	 */
	public final static String version_update_error = "1003001";
	
	/**
	 * 版本无更新
	 */
	public final static String version_noupdate_error = "1003002";
	
	/**
	 * 第三方异常
	 */
	public final static String ThirdParty_ERROR="1003003";
	
	/**
	 * 重复的支付请求
	 */
	public final static String repeat_PayRequest="1003004";

	/**
	 * 微信登录异常-无效的Code
	 */
	public final static String Wxlogin_ErrorCode="40029";
	
	/**
	 * 微信登录异常-无效的openId
	 */
	public final static String Wxlogin_ErrorOpenid="40003";
}
