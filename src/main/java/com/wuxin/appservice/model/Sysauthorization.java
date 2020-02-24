package com.wuxin.appservice.model;

import java.util.Map;
import java.util.TreeMap;

//import interfacedemo.InterfaceParameVo;
//import interfacedemo.KeqiHelper;

public class Sysauthorization {
    private Integer id;

    private Integer roleid;

    private Integer urlid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getUrlid() {
        return urlid;
    }

    public void setUrlid(Integer urlid) {
        this.urlid = urlid;
    }
    public static void main(String[] args) {
		//InterfaceParameVo invo = new InterfaceParameVo();//http://127.0.0.1:8089/zx/businessInteface/business
//		invo.setQueryUserName("123123");
//		invo.setPwd("123123");
//		invo.setUserName("鹏元二");
//		invo.setIdCardNo("111111111");
//		invo.setSearchIds("10");
//		TreeMap<String, String> paramsMap = new TreeMap<>();
//		paramsMap.put("queryUserName", invo.getQueryUserName());
//		paramsMap.put("pwd", invo.getPwd());
//		paramsMap.put("userName", invo.getUserName());
//		paramsMap.put("idCardNo", invo.getIdCardNo());
//		paramsMap.put("bankCardNo", invo.getBankCardNo());
//		paramsMap.put("carNo", invo.getCarNo());
//		paramsMap.put("phone", invo.getPhone());
//		paramsMap.put("beginDate", invo.getBeginDate());
//		paramsMap.put("endDate", invo.getEndDate());
//		paramsMap.put("companyName", invo.getCompanyName());
//		paramsMap.put("searchIds", invo.getSearchIds());
		//公钥
		String publickey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDcCfd+c2OPZmVcIw1xEsSi95BrrC/"+
	  			"IgGCc4c3qF2wo+HEUzHHx5FuXyhpRbd7gbc3czVwzcuYKUngurt0ADpkLqhIF3rvs2sS5MhUbxs0"
	  			+ "zIFaohg4Fi53LpxooqT+69JyVxbezZhoaFomNDqWnHFtlfHBK4P64hq8jjEdnXAjWxwIDAQAB";
		try {
//			Map<String, String> map = KeqiHelper
//					.callKeqiInteface("http://127.0.0.1:8089/zx/businessInteface/business", paramsMap,publickey);
//			System.out.println(map.get("result"));
//			System.out.println(map.get("signIsPass"));
			
		} catch (Exception e) {			
			e.printStackTrace();
		}

	}
}