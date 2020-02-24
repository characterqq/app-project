package com.wuxin.appservice.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * xml工具类
 * @author wuxy
 *
 */
public class XMLUtil {

	/**
	 * XML解析
	 * @param strxml 需要解析的XML
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static SortedMap doXMLParse(String strxml) throws IOException {
		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		SortedMap parameters=new TreeMap();
        Document doc = null;  
        try {  
            doc = DocumentHelper.parseText(strxml); // 将字符串转为XML  
            Element rootElt = doc.getRootElement(); // 获取根节点  
            List<Element> list = rootElt.elements();//获取根节点下所有节点  
            for (Element element : list) {  //遍历节点  
            	parameters.put(element.getName(), element.getText()); //节点的name为map的key，text为map的value  
            }  
        } catch (DocumentException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return parameters;  
	}
	
	/**
	 * 获取XML编码
	 * @param strxml
	 * @return
	 * @throws DocumentException 
	 */
	public static String getXMLEncoding(String strxml) throws  DocumentException {
		Document doc = DocumentHelper.parseText(strxml); // 将字符串转为XML 
		return doc.getXMLEncoding();
	}
	
	public static InputStream string2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
}

