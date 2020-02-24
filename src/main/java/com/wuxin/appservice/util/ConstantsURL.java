package com.wuxin.appservice.util;

/**
 * 接口URL参数配置
 * @author wuxy
 *
 */
public class ConstantsURL {
	/**
	 * 微信登录获取access_token请求URL
	 */
    public final static String WXLOGIN_ACCESSTOKEN="https://api.weixin.qq.com/sns/oauth2/access_token";
    
    /**
     * 微信登录获取用户信息URL
     */
    public final static String WXLOGIN_GETUSERINFO="https://api.weixin.qq.com/sns/userinfo";
    
    /**
     * 微信支付统一下单API地址
     */
    public final static String WXPay_unifiedorder="http://apitest.tfb8.com/cgi-bin/v2.0/api_wx_pay_apply.cgi";
   // http://upay.tfb8.com/cgi-bin/v2.0/api_wx_pay_apply.cgi 生产环境下的api地址
    
    /**
	 * 财付通获取token_id接口地址
	 */
	public final static String cft_wappay_init="https://www.tenpay.com/app/mpay/wappay_init.cgi";
    
    /**
     * 微信-应用密钥AppSecret
     */
    public final static String wx_secret="70bccd242b013d16dc0fb9cbe0ab2ef0";
}
