package com.wuxin.appservice.util;

public class KjPayConfig {

	// 接口域名，测试访问域名（正式环境改为：api.tfb8.com）
//  public static String hostName = "http://apitest.tfb8.com";
	public static String hostName = "http://api.tfb8.com";
  

  // MD5密钥，国采分配给商户的用于签名和验签的key
  public static String key = "fa6d88479ce111e698eb6c0b84b7aa1a";
//	public static String key = "12345";
	
  // 商户/平台在国采注册的账号。国采维度唯一，固定长度10位
  public static String spid = "1800364090";
//	public static String spid = "1800071515";
	
  // 用户号，持卡人在商户/平台注册的账号。商户/平台维度唯一，必须为纯数字
//  public static String sp_userid = "666666666";	//对应农行卡号
  public static String sp_userid = "888866669";	//对应建行卡号
  
  // 订单有效时长，以国采服务器时间为准的订单有效时间长度。单位:秒，如果不填则采用默认值
  public static String expire_time = "";

  // 订单金额的类型。1 – 人民币(单位: 分)
  public static String cur_type = "1";

  // 商户的用户使用的终端类型。1 – PC端，2 – 手机端
  public static String channel = "1";

  // 签名的方法。目前支持: MD5，RSA
  public static String encode_type = "MD5";

  // 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
  public static String notify_url = "http://222.73.159.70:8093/WXDB/interface/weixinPayNotify";

  // 商户的私钥,需要PKCS8格式
//  public static String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAOxfCXRspBxaFfdkLxssn4aTcdQWYk/Ig2xplQwtfaa5bD2o+WwqRqvnjG9U1SLfyEVRONU/2W6HmJVAAvHNP13e+KeoPgWfDIbAIOT2QpSdxc2RYHi3AAn6d5BpHunnHgENIAn83OKx8aUjMZrG9sb2DuHj3HplXmzx48zdV+QdAgMBAAECgYA472NtocWd9q0X3A/kscjzN4zRk1q28QuHD5+RQVvkzwEdkRgTT9OaELopDvwP3Gt3Futjom+TSiIcwUB0UgYS9eH8koKYYEERrdCjIwaETD/XVEpUw6IU9fvRb1KdyX34cR4FtnL8YAadbl2i25s0tw9ekxbheFZ3NNcIjD3bgQJBAPfi/DZaDQ96OxYIY/z0VlegY9CbyF3HXcd2og+M+iEQS1Qhsz75X0PKJAIzdYBh8ewEbSpxIbzazaH7xfCrVc0CQQD0G5C2HL+HRwSOIfWDk5kC6JtG/CIxFkh/JUQvBB43k2cuyRn8T/24xLhIajpFzUtSLBiB7xVWnq3zd1RbEneRAkEAtwPM9kvyDJVP/mpfeCjlfniaeTAq9KH3KQb7i8OoALVJCGqBLmNq5F/fygg4Hnsx4/E4r/cq04YwAAIlGBVHmQJBAMlI0ZLZMbVCgicRKXlNg6Y6V4+46BjB3xW4jPiX1LLPAQ47FQpQi06IJ8zOiy5zkeA4LB04H8hFH/raMtzF5YECQQCgg4GbAoLLs2TquQqN+xBfUGc/0Uc/yBDGOtrWDOL1ldRQHdg6U+1y34dDkGdzALZieIRi0LqwszuQbT3JY68K";

  // 天付宝公钥
//  public static String TFB_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCjDrkoVbyv4jTxeKtKEiK2mZiezQvfJV3sGhiwOnB+By5sa5Sa6Ls4dt5AGVqKHxyQVKRpu/utwtEt2MijWx45P1y2xGe7oDz2hUXP0j8sSa1NP26TmWHwO7czgJxxrdJ6RNqskSfjwsa5YMsqmcrumxUIxeCg5EOkgU26bnPoZQIDAQAB";
  
  /** 正式密钥*/
  // 商户的私钥,需要PKCS8格式
	public static String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAO4w929Wbb0XemzkX8S2wiwGIqFfy/scH2C/qjxbO3dMyRy3pyuVtlCD9FW8hAiPrTKhmSGJjexna7q7X6ecqcyVwLRLOgGkc/hGhrQ0Q6cJ9LGHUyGsND4A9vEi84TrbOiXpQEqa4X6FZAiqMILtwT1CaW0OrOtuVNVLDBQYKyPAgMBAAECgYA1p8wlRXfk76QBYZR9MJoe5qboaTb+8v/SgHptrDOCFUb8dBZxSAFQs9jXc2bestbWH/bpyh2xUj6/SrQqgqzw3Mu25j5om4ZZq1vD+6sgVUPhLPAc/DNKsoyIDtDbiL9W4VWRblFK7c1RfuQSlFACuD+6s8i1znASfUWwwF84+QJBAPp2L3aZNUC2UZzYv2IBKl34l23ud1L4SZ6tc4THQmaaSr2UxI5OVXo/utwaekIYTtix/VHsk2JbEtspVZAFFlUCQQDzdVIlA5c0/b0bTiSw+yo10mhKlWlMYUYCVljQormfC0HzuEWPQIaB4I/SmznJeJQnOXxhwq4EV81qWXQKqbNTAkBCuQdt7eS855UYVrmtbygNPrOAqr2JEfHy3rxejALJeKMenCo8yuuElMO/7SHSx80Bd3FHyOF+m0qLrhhINMK9AkBzIx7wJlySURXoHfK4SHBT9+gerb6YwsArRs7GDM8gamCMdK9g6jEypxMdlQ+lOoNGcadjTICjySp6i0eX613FAkEApSullticn18fF29FBBBqu2rnceaIWuBD5+gkzWQaJ/gu5TXVX/R3Iw2nW1vJk6JFRqgeEjRNDehVUnQPjQsULg==";
  // 天付宝公钥
	public static String TFB_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCjDrkoVbyv4jTxeKtKEiK2mZiezQvfJV3sGhiwOnB+By5sa5Sa6Ls4dt5AGVqKHxyQVKRpu/utwtEt2MijWx45P1y2xGe7oDz2hUXP0j8sSa1NP26TmWHwO7czgJxxrdJ6RNqskSfjwsa5YMsqmcrumxUIxeCg5EOkgU26bnPoZQIDAQAB";
   
  // 银行卡快捷签约支付申请调用的接口名
  public static String qsignPayApplyApi = hostName + "/cgi-bin/v2.0/api_qsignpay_apply.cgi";
//	public static String qsignPayApplyApi = hostName + "/cgi-bin/v2.0/api_safe_qsignpay_apply.cgi";
	
  // 银行卡快捷签约支付确认调用的接口名
  public static String qsignPayComfirmApi = hostName + "/cgi-bin/v2.0/api_qsignpay_confirm.cgi";

  // 银行卡快捷支付申请调用的接口名
  public static String bankCardPayApi = hostName + "/cgi-bin/v2.0/api_qpay_apply.cgi";
//  												  /cgi-bin/v2.0/api_qpay_apply.cgi
  
  // 银行卡快捷支付确认调用的接口名
  public static String bankCardPayComfirmApi = hostName + "/cgi-bin/v2.0/api_qpay_confirm.cgi";
//  														 /cgi-bin/v2.0/api_qpay_confirm.cgi
  
  // 银行卡快捷解约调用的接口名
  public static String qsignCancelApi = hostName + "/cgi-bin/v2.0/api_qsign_cancel.cgi";
//  												  /cgi-bin/v2.0/api_qsign_cancel.cgi

  // 支付结果单笔查询调用的接口名
  public static String orderQueryApi = hostName + "/cgi-bin/v2.0/api_single_qry_order.cgi";
//  												 /cgi-bin/v2.0/api_single_qry_order.cgi

  // 快捷签约协议单笔查询调用的接口名
  public static String qsignQueryApi = hostName + "/cgi-bin/v2.0/api_single_qry_qsign.cgi";
//  												 /cgi-bin/v2.0/api_single_qry_qsign.cgi

  // 银行卡信息单笔查询调用的接口名
  public static String cardbinQueryApi = hostName + "/cgi-bin/v2.0/api_single_qry_cardbin.cgi";
//  												   /cgi-bin/v2.0/api_single_qry_cardbin.cgi

  // 银行限额单笔查询调用的接口名
  public static String quotaQueryApi = hostName + "/cgi-bin/v2.0/api_single_qry_quota.cgi";
//  												 /cgi-bin/v2.0/api_single_qry_quota.cgi

  // 快捷验证码短信重发调用的接口名
  public static String smsResendApi = hostName + "/cgi-bin/v2.0/api_sms_resend.cgi";
//  												/cgi-bin/v2.0/api_sms_resend.cgi

  // 服务器数据编码类型
  public static String serverEncodeType = "GBK";
}
