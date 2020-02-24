package com.wuxin.appservice.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
public class RequestUtils {

	public static TreeMap<String, String> Dom2Map(String xml) throws DocumentException {
		Document doc = DocumentHelper.parseText(xml);
		TreeMap<String, String> map = new TreeMap<String, String>();
		if (doc == null)
			return map;
		Element root = doc.getRootElement();
		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			List list = e.elements();
			// if (list.size() > 0) {
			// map.put(e.getName(), Dom2Map(e));
			// } else {
			map.put(e.getName(), e.getText());
			// }
		}
		return map;
	}

	public static Map Dom2Map(Element e) {
		Map map = new HashMap();
		List list = e.elements();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Element iter = (Element) list.get(i);
				List mapList = new ArrayList();

				if (iter.elements().size() > 0) {
					Map m = Dom2Map(iter);
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(m);
						}
						if (obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = (List) obj;
							mapList.add(m);
						}
						map.put(iter.getName(), mapList);
					} else
						map.put(iter.getName(), m);
				} else {
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(iter.getText());
						}
						if (obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = (List) obj;
							mapList.add(iter.getText());
						}
						map.put(iter.getName(), mapList);
					} else
						map.put(iter.getName(), iter.getText());
				}
			}
		} else
			map.put(e.getName(), e.getText());
		return map;
	}

	/**
	 * 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
	 */
	public static String getParamSrc(TreeMap<String, String> paramsMap) {
		StringBuffer paramstr = new StringBuffer();
		for (String pkey : paramsMap.keySet()) {
			String pvalue = paramsMap.get(pkey);
			if (null != pvalue && "" != pvalue && pkey != "sign" && pkey != "retcode" && pkey != "retmsg"
					&& pkey != "sign_type") {// 空值不传递，不签名
				paramstr.append(pkey + "=" + pvalue + "&"); // 签名原串，不url编码
			}
		}
		// 去掉最后一个&
		String result = paramstr.substring(0, paramstr.length() - 1);
		// 原串转码
		try {
			result = new String(result.getBytes(TFBConfig.serverEncodeType), TFBConfig.serverEncodeType);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getEncodeParamSrc(TreeMap<String, String> paramsMap) throws UnsupportedEncodingException {
		StringBuffer paramstr = new StringBuffer();
		for (String pkey : paramsMap.keySet()) {
			String pvalue = paramsMap.get(pkey);
			if (null != pvalue && "" != pvalue && pkey != "retcode" && pkey != "retmsg" && pkey != "sign_type") {// 空值不传递，不签名
				paramstr.append(pkey + "=" + URLEncoder.encode(pvalue, TFBConfig.serverEncodeType) + "&"); // 签名原串，不url编码
			}
		}
		// 去掉最后一个&
		String result = paramstr.substring(0, paramstr.length() - 1);
		// 原串转码
		try {
			result = new String(result.getBytes(TFBConfig.serverEncodeType), TFBConfig.serverEncodeType);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 分解解密后的字符串，保存为map
	 */
	public static HashMap<String, String> parseString(String responseData) {
		HashMap<String, String> map = new HashMap<String, String>();
		String[] s1 = responseData.split("&");
		String[] s2 = new String[2];
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s1.length; i++) {
			s2 = s1[i].split("=", 2);
			map.put(s2[0], s2[1]);
			if (!s2[0].equals("sign")) {
				sb.append(s2[0] + "=" + s2[1] + "&");
			}
		}
		String source = sb.substring(0, sb.length() - 1);
		map.put("source", source);
		return map;
	}

	/**
	 * 解析xml
	 */
	public static String getXmlElement(String responseData, String element) {
		String result = null;

		try {
			Document dom = DocumentHelper.parseText(responseData);
			Element root = dom.getRootElement();
			result = root.element(element).getText();
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public static String doPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), TFBConfig.serverEncodeType));
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), TFBConfig.serverEncodeType));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
