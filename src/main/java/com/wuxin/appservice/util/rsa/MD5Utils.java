package com.wuxin.appservice.util.rsa;

import java.security.MessageDigest;

import com.wuxin.appservice.util.rsa.config.RsaConfig;


public class MD5Utils {

    /**
     * MD5签名
     * 
     * @param paramSrc
     *            the source to be signed
     * @return
     * @throws Exception
     */
    public static String sign(String paramSrc) {
        String sign = md5(paramSrc + "&key=" + RsaConfig.key);
        System.out.println("MD5签名结果：" + sign);
        return sign;
    }

    /**
     * MD5验签
     * 
     * @param source
     *            签名内容
     * @param sign
     *            签名值
     * @return
     */
    public static boolean verify(String source, String tfbSign) {
        String sign = md5(source + "&key=" + RsaConfig.key);
        System.out.println("自签结果：" + sign);
        return tfbSign.equals(sign);
    }

    public static void main(String[] args) {
        String s = "attach=abc123_&bank_segment=1001&card_type=1&channel=1&cur_type=1&encode_type=MD5&memo=订单的商品的名称&money=1&notify_url=http://jucailue.com//tfb_qpay-JAVA-UTF8-MD5/notify_url.jsp&return_url=http://jucailue.com//tfb_qpay-JAVA-UTF8-MD5/notify_url.jsp&sp_userid=101347613&spbillno=20160823160701&spid=1800624531&user_type=1&key=6Iv3t*5u#1";
        System.out.println(md5(s));
    }
    
    public final static String md5(String paramSrc) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = paramSrc.getBytes(RsaConfig.serverEncodeType);
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
