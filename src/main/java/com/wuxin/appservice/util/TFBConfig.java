package com.wuxin.appservice.util;

public class TFBConfig {

	// 接口域名，测试访问域名（正式环境改为：api.tfb8.com）
	public static String hostName = "http://api.tfb8.com";

	// MD5密钥，安全检验码
	public static String key = "fa6d88479ce111e698eb6c0b84b7aa1a";

	// 服务器数据编码类型
	public static String serverEncodeType = "GBK";

	// 商户/平台在国采注册的账号。国采维度唯一，固定长度10位
	public static String spid = "1800364090";

	// 订单金额的类型。1 – 人民币(单位: 分)
	public static String cur_type = "CNY";

	// 商户发起支付请求的IP
	public static String spbill_create_ip = "192.168.11.96";

	// 设备id
	public static String sp_udid = "";

	// 支付申请接口名http://upay.tfb8.com/cgi-bin/v2.0/api_wx_pay_apply.cgi
	public static String payApplyApi = "http://upay.tfb8.com/cgi-bin/v2.0/api_wx_pay_apply.cgi";
	
	// 公众号支付申请测试接口
	public static String subpayApplyApi = "http://upay.tfb8.com/test/cgi-bin/v2.0/api_wx_subpay_apply.cgi";
	
	// 订单关闭接口名
	public static String payCancelApi = hostName + "/cgi-bin/v2.0/api_wx_pay_cancel.cgi";

	// 单笔支付单查询接口名
	public static String paySingleQueryApi = hostName + "/cgi-bin/v2.0/api_wx_pay_single_qry.cgi";

	// 批量支付单查询接口名
	public static String payBatchQueryApi = hostName + "/cgi-bin/v2.0/api_wx_pay_batch_qry.cgi";

	// 退款接口名
	public static String refundApi = hostName + "/cgi-bin/v2.0/api_wx_refund.cgi";

	// 单笔退款单查询接口名
	public static String refundSingleQueryApi = hostName + "/cgi-bin/v2.0/api_wx_refund_single_qry.cgi";

	// 批量退款单查询接口名
	public static String refundBatchQueryApi = hostName + "/cgi-bin/v2.0/api_wx_refund_batch_qry.cgi";

	// 下载账单接口名
	public static String downloadBillApi = hostName + "/cgi-bin/v2.0/api_wx_pay_downloadbill.cgi";

}
