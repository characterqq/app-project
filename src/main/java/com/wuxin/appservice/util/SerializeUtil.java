package com.wuxin.appservice.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * 
 * @author hk
 *
 */
public class SerializeUtil {
	/**
	 * 序列化
	 * serialize:
	 * TODO
	 * @param  @param object
	 * @param  @return
	 * @return byte[]  
	 * @throws 
	 * @since  zhuzx Ver 1.0
	 */
      public static byte[] serialize(Object object) {
           ObjectOutputStream oos = null;
            ByteArrayOutputStream baos = null;
            try {
                 // 序列化
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                 byte[] bytes = baos.toByteArray();
                 return bytes;
           } catch (Exception e) {
        	   //e.printStackTrace();
           }
            return null;
     }
/**
 * 反序列化
 * unserialize:
 * TODO
 * @param  @param bytes
 * @param  @return
 * @return Object  
 * @throws 
 * @since  zhuzx Ver 1.0
 */
      public static Object unserialize( byte[] bytes) {
           ByteArrayInputStream bais = null;
            try {
                 // 反序列化
                bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                 return ois.readObject();
           } catch (Exception e) {
        	   //e.printStackTrace();
           }
            return null;
     }
}
