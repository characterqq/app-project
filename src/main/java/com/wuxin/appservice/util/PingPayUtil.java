//package com.wuxin.appservice.util;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import com.pingplusplus.Pingpp;
//import com.pingplusplus.model.Charge;
//import com.wuxin.appservice.vo.PingPayVo;
//
///**
// * ping++支付工具类
// * @author wuxy
// *
// */
//public class PingPayUtil {
//
//	/**
//	 * 货币编号--人民币
//	 */
//	private final static String currency="cny";
//	
//	/**
//	 * ping++提供的api key
//	 */
//	private final static String server_apikey="sk_test_rrXbDS9GGKC0jfjfHOLufv10";
//	
//	/**
//	 * 开启支付，返回Charge对象
//	 * @return
//	 */
//	public Charge startPay(PingPayVo ppv){
//		try { 
//		  Pingpp.apiKey = server_apikey;
//
//		  Map<String, Object> chargeParams = new HashMap<String, Object>();
//		  chargeParams.put("order_no",ppv.getOrder_no());//订单号
//		  chargeParams.put("amount",ppv.getAmount());//订单总金额(单位：分)
//		  Map<String, String> app = new HashMap<String, String>();
//		  app.put("id", ppv.getAppId());//第三方支付appId
//		  chargeParams.put("app", app);
//		  chargeParams.put("channel",ppv.getChannel());//第三方支付编号ping++提供
//		  chargeParams.put("currency",currency);//货币代码-cny人民币
//		  chargeParams.put("client_ip",ppv.getClient_ip());//客户端IP
//		  chargeParams.put("subject",ppv.getSubject());//商品标题
//		  chargeParams.put("body",ppv.getBody());//商品描述
//		  return Charge.create(chargeParams);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//}
