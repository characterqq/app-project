package com.wuxin.appservice.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * HttpClient工具类
 * @author wuxy
 *
 */
public class HttpClientUtil {
	/**
	 * 获取HttpGet
	 * @param url
	 * @return
	 */
    public static HttpGet getHttpGet(String url){
    	 HttpGet  httpget= new HttpGet(url);
		 return httpget;
    }
    
    /**
     * 获取httpPost
     * @param url
     * @return
     */
    public static HttpPost getHttpPost(String url){
    	return new HttpPost(url);
    }
    
    /**
     * 执行HTTP GET请求
     * @param url 请求URL
     * @return 请求结果(String)
     */
    public static String createGetExecute(String url){
    	HttpGet httpget=getHttpGet(url);
	    CloseableHttpClient httpclient = HttpClients.createDefault();
	    CloseableHttpResponse response=null;
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() >= 400) {
			      throw new IOException("Got bad response, error code = " + response.getStatusLine().getStatusCode());
			}else{
				if(entity!=null){
					return EntityUtils.toString(entity, "UTF-8");
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
			 if(response!=null){
					response.close();
			  }
			  if(httpclient!=null){
				  httpclient.close();  
			  }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
    }

    /**
     * 执行HTTP POST请求,返回String形式响应结果
     * @param url 请求URL
     * @param params 参数
     * @return 响应结果(String)
     */
    public static String createPostExecute(String url,String params)throws Exception{
    	CloseableHttpResponse response = null; 
    	try {
	    	// 创建默认的httpClient实例.    
	        CloseableHttpClient httpclient = HttpClients.createDefault();
	        //获取httpPost
	        HttpPost httpPost=new HttpPost(url);
	        httpPost.setEntity(new StringEntity(params, "UTF-8"));
	        //执行请求
			response = httpclient.execute(httpPost);
			//获取结果
			HttpEntity entity = response.getEntity();  
			return EntityUtils.toString(entity, "UTF-8");
	     } catch (IOException e) {
	    	 throw e;
		 }finally {
			  if(response!=null){
				  try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			  }
		 }
    }
    
    /**
     * 批量文件上传接口
     * @param fileMap 文件列表 key=保存文件名 value=输入流
     * @param url 接口URL
     * @param saveUrl 文件保存路径(相对路径)
     * @return 响应结果
     */
    public static String postFiles(Map<String,InputStream> fileMap,String url,String saveUrl){
    	// 创建默认的httpClient实例.    
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();  
        if(!fileMap.isEmpty()){
        	for (Entry<String, InputStream> entry : fileMap.entrySet()) {
        	   builder.addBinaryBody(entry.getKey(), entry.getValue());
        	}
        }
        builder.addTextBody("saveUrl", saveUrl);
        builder.addTextBody("sign", "wxwl365");
        httpPost.setEntity(builder.build());
        try {
			HttpResponse response = httpClient.execute(httpPost);
			 if(response.getStatusLine().getStatusCode() == 200){
				  String result = EntityUtils.toString(response.getEntity(),"UTF-8");
				  return result;
			 }
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(!fileMap.isEmpty()){
				for (Entry<String, InputStream> entry : fileMap.entrySet()) {
		        	try {
						entry.getValue().close();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
			}
		}
    	return "";
    }
    
    /**
     * 单文件上传
     * @param file 上传文件输入流
     * @param url 接口URL
     * @param saveUrl 文件保存相对路径
     * @param fileName 文件名
     * @return 
     */
    public static String postFile(InputStream file,String url,String saveUrl,String fileName){
    	// 创建默认的httpClient实例.    
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();  
        builder.addBinaryBody("file",file);
        builder.addTextBody("saveUrl", saveUrl);
        builder.addTextBody("sign", "wxwl365");
        builder.addTextBody("fileName",fileName);
        httpPost.setEntity(builder.build());
        try {
			HttpResponse response = httpClient.execute(httpPost);
			 if(response.getStatusLine().getStatusCode() == 200){
				  String result = EntityUtils.toString(response.getEntity(),"UTF-8");
				  return result;
			 }
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(file!=null){
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    	return "";
    }
}
