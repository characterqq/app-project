//package com.wuxin.appservice.timer;
//
//import java.io.IOException;
//import java.util.Date;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.wuxin.appservice.model.HelpLotteryResult;
//import com.wuxin.appservice.service.HelpLotteryResultService;
//import com.wuxin.appservice.util.DateUtil;
//import com.wuxin.appservice.util.HttpClientUtil;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
///**
// * 定时任务
// * @author wuxy
// *
// */
//@Component
//public class Timer {
//	
//	protected static final Logger LOG = Logger.getLogger(Timer.class);
//	
//	/**
//	 * 百度时时彩
//	 */
//	private final static String baiduSSCUrl="http://baidu.lecai.com/lottery/ajax_latestdrawn.php?lottery_type=200";
//	
//	/**
//	 * 开彩网时时彩免费API地址
//	 */
//	private final static String kcwSSCUrl="http://f.apiplus.cn/cqssc-1.json";
//	
//	@Autowired
//	private HelpLotteryResultService helpLotteryResultService;
//
//	/**
//	 * 获取重庆时时彩
//	 */
//	public void findSSCResult(){
//			int i=0;
//			//1.获取开彩网彩票开奖结果
//			JSONObject json=Timer.createhttpClientByGet(kcwSSCUrl);
//			
//			if(json==null){
//				if(i==0){
//					//开彩网获取失败，获取百度彩票开奖结果
//					json=Timer.createhttpClientByGet(baiduSSCUrl);
//					i++;
//				}
//				if(json==null){
//					LOG.info("获取时时彩,二次抓取失败");
//					return;
//				}
//			}
//			HelpLotteryResult hlr=null;
//			if(i==0){//开彩网开奖结果解析
//				hlr=parseKCWJson(json);
//			}else{//百度开奖结果解析
//				hlr=parseBaiduJson(json);
//			}
//			//保存结果
//			helpLotteryResultService.insertHelpLotteryResult(hlr);
//			LOG.info("开奖结果："+hlr.getPeriod()+"="+hlr.getNumber());
//	}
//	
//	/**
//	 * Get方式请求百度彩票URL
//	 * @param url
//	 * @return
//	 * @throws IOException
//	 */
//   private static JSONObject createhttpClientByGet(String url){
//		    HttpGet httpget=HttpClientUtil.getHttpGet(url);
//		    CloseableHttpClient httpclient = HttpClients.createDefault();
//		    RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20*1000).setConnectTimeout(20*1000).build();//设置请求和传输超时时间20s
//		    httpget.setConfig(requestConfig);
//			CloseableHttpResponse response=null;
//			try {
//				response = httpclient.execute(httpget);
//				HttpEntity entity = response.getEntity();
//				if (response.getStatusLine().getStatusCode() >= 400) {
//					LOG.error("获取时时彩开奖结果失败,error code="+response.getStatusLine().getStatusCode());
//				}else{
//					if(entity!=null){
//						return JSONObject.fromObject(EntityUtils.toString(entity, "UTF-8"));
//					}
//				}
//			} catch (ClientProtocolException e) {
//				LOG.error("获取时时彩开奖结果失败,异常："+e);
//			} catch (IOException e) {
//				LOG.error("获取时时彩开奖结果失败,异常："+e);
//			}finally {
//				try {
//				 if(response!=null){
//						response.close();
//				  }
//				  if(httpclient!=null){
//					  httpclient.close();  
//				  }
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			return null;
//	}
//	
//	/**
//	 * 解析开彩网提供的免费API json数据
//	 * @param json
//	 * @return HelpLotteryResult
//	 */
//   private static HelpLotteryResult parseKCWJson(JSONObject json){
//		HelpLotteryResult hlr=new HelpLotteryResult();
//		JSONArray datas=json.getJSONArray("data");
//		JSONObject data=datas.getJSONObject(0);
//		String number=data.getString("opencode").replace(",","").trim();
//		hlr.setPeriod(data.getString("expect"));
//		hlr.setNumber(number);
//		hlr.setLotteryDate(DateUtil.SDateTimeToDate(data.getString("opentime")));
//		hlr.setCreateDate(new Date());
//		return hlr;
//	}
//	
//	/**
//	 * 解析百度彩票抓取的JSON 数据
//	 * @param json
//	 * @return HelpLotteryResult
//	 */
//   private static HelpLotteryResult parseBaiduJson(JSONObject json){
//		JSONArray data=json.getJSONArray("data");
//		JSONObject dataObject=(JSONObject) data.get(0);
//		JSONObject result=(JSONObject) dataObject.get("result");
//		JSONArray results=result.getJSONArray("result");
//		String jsonNumber=results.getJSONObject(0).getString("data");
//		jsonNumber=jsonNumber.replace("[","");
//		jsonNumber=jsonNumber.replace("]","");
//		String [] numbers=jsonNumber.split(",");
//		String number="";
//		for (String nb : numbers) {
//			number=number+nb.replace("\"","").trim();
//		}
//		HelpLotteryResult hlr=new HelpLotteryResult();
//		hlr.setPeriod(dataObject.getString("phase"));
//		hlr.setNumber(number);
//		hlr.setLotteryDate(DateUtil.SDateTimeToDate(dataObject.getString("time_startsale")));
//		hlr.setCreateDate(new Date());
//		return hlr;
//	}
//}
