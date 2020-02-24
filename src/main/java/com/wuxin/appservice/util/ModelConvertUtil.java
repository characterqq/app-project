package com.wuxin.appservice.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

/**
 * model值转换工具类
 * get字段()==null?"":get字段()
 * @author wuxy
 *
 */
public class ModelConvertUtil {

	/*public static void main(String[] args) {
		try {
		 UserVo uv=(UserVo) getObjectValue(new UserVo());
		 System.out.println(uv.getMobile()+"---"+uv.getLoginType()+"---"+uv.getBalance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public static Object valueConvert(Object object) throws Exception {  
        if (object != null) { 
            // 拿到该类  
            Class<?> clz = object.getClass();  
            // 获取实体类的所有属性，返回Field数组  
            Field[] fields = clz.getDeclaredFields();  
  
            for (Field field : fields) {// --for() begin  
//                System.out.println(field.getGenericType());//打印该类的所有属性类型  
                // 如果类型是String  
                if (field.getGenericType().toString().equals(  
                        "class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名  
                    // 拿到该属性的gettet方法  
                    /** 
                     * 这里需要说明一下：他是根据拼凑的字符来找你写的getter方法的 
                     * 在Boolean值的时候是isXXX（默认使用ide生成getter的都是isXXX） 
                     * 如果出现NoSuchMethod异常 就说明它找不到那个gettet方法 需要做个规范 
                     */  
                    Method m = (Method) object.getClass().getMethod(  
                            "get" + getMethodName(field.getName()));  
  
                    String val = (String) m.invoke(object);// 调用getter方法获取属性值  
                    if (val == null) {  
                    	m = object.getClass().getMethod("set"+getMethodName(field.getName()),String.class);
                        m.invoke(object, "");
                    }  
                }  
  
                // 如果类型是Integer  
                if (field.getGenericType().toString().equals(  
                        "class java.lang.Integer")) {  
                    Method m = (Method) object.getClass().getMethod(  
                            "get" + getMethodName(field.getName()));  
                    Integer val = (Integer) m.invoke(object);  
                    if (val == null) {  
                    	m = object.getClass().getMethod("set"+getMethodName(field.getName()),Integer.class);
                        m.invoke(object,0);
                    }  
  
                }  
  
                // 如果类型是Double  
                if (field.getGenericType().toString().equals(  
                        "class java.lang.Double")) {  
                    Method m = (Method) object.getClass().getMethod(  
                            "get" + getMethodName(field.getName()));  
                    Double val = (Double) m.invoke(object);  
                    if (val != null) {  
                    	
                    }  
  
                }  
  
                // 如果类型是Boolean 是封装类  
                if (field.getGenericType().toString().equals(  
                        "class java.lang.Boolean")) {  
                    Method m = (Method) object.getClass().getMethod(  
                            field.getName());  
                    Boolean val = (Boolean) m.invoke(object);  
                    if (val != null) {  
                    	
                    }  
  
                }  
  
                // 如果类型是Date  
                if (field.getGenericType().toString().equals(  
                        "class java.util.Date")) {  
                    Method m = (Method) object.getClass().getMethod(  
                            "get" + getMethodName(field.getName()));  
                    Date val = (Date) m.invoke(object);  
                    if (val != null) {  
                    	
                    }  
  
                }  
                // 如果类型是Short  
                if (field.getGenericType().toString().equals(  
                        "class java.lang.Short")) {  
                    Method m = (Method) object.getClass().getMethod(  
                            "get" + getMethodName(field.getName()));  
                    Short val = (Short) m.invoke(object);  
                    if (val != null) { 
                    	
                    }  
                }
                
                // 如果类型是BigDecimal  
                if (field.getGenericType().toString().equals(  
                        "class java.math.BigDecimal")) {  
                    Method m = (Method) object.getClass().getMethod(  
                            "get" + getMethodName(field.getName()));  
                    BigDecimal val = (BigDecimal) m.invoke(object);  
                    if (val == null) {  
                    	m = object.getClass().getMethod("set"+getMethodName(field.getName()),BigDecimal.class);
                        m.invoke(object,BigDecimal.ZERO);
                    }  
                }
                // 如果还需要其他的类型请自行做扩展  
            }//for end
        }//if end  
      return object;
    }  
  
    // 把一个字符串的第一个字母大写、效率是最高的、  
    private static String getMethodName(String fildeName) throws Exception{  
        byte[] items = fildeName.getBytes();  
        items[0] = (byte) ((char) items[0] - 'a' + 'A');  
        return new String(items);  
    }  
}
