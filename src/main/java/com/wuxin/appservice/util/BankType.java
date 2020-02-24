package com.wuxin.appservice.util;

import org.apache.commons.lang.StringUtils;



/**
 * 银行卡类型
 * @author wuxy
 */
public class BankType {
	/**
	 * 工商银行	
	 */
	public final static int bank_GS=1;
	/**
	 * 农业银行
	 */
	public final static int bank_NY=2;
	/**
	 * 建设银行
	 */
	public final static int bank_JS=3;
	/**
	 * 光大银行
	 */
	public final static int bank_GD=4;
	/**
	 * 交通银行
	 */
	public final static int bank_JT=5;
	/**
	 * 浦发银行
	 */
	public final static int bank_PF=6;
	/**
	 * 招商银行
	 */
	public final static int bank_ZS=7;
	/**
	 * 中国银行
	 */
	public final static int bank_ZG=8;
	/**
	 * 邮政储蓄银行
	 */
	public final static int bank_YZCX=9;
	/**
	 * 中信银行
	 */
	public final static int bank_ZX=10;
	/**
	 * 兴业银行
	 */
	public final static int bank_XY=11;
	/**
	 * 民生银行
	 */
	public final static int bank_MS=12;
	/**
	 * 深圳发展银行
	 */
	public final static int bank_SZFZ=13;
	/**
	 * 北京银行
	 */
	public final static int bank_BJ=14;
	/**
	 * 广发银行
	 */
	public final static int bank_GF=15;
	/**
	 * 上海银行
	 */
	public final static int bank_SH=16;
	/**
	 * 平安银行
	 */
	public final static int bank_PA=17;
	
	/**
	 * 根据抓取的字符串获取银行ID
	 * @param resultItem
	 * @return
	 */
	public static int selectBankId(String resultItem){
		int bankId=-1;
		if(StringUtils.isNotEmpty(resultItem)){
			if(resultItem.indexOf("工商银行")!=-1){
				bankId=bank_GS;
			}else if(resultItem.indexOf("农业银行")!=-1){
				bankId=bank_NY;
			}else if(resultItem.indexOf("建设银行")!=-1){
				bankId=bank_JS;
			}else if(resultItem.indexOf("光大银行")!=-1){
				bankId=bank_GD;
			}else if(resultItem.indexOf("交通银行")!=-1){
				bankId=bank_JT;
			}else if(resultItem.indexOf("浦东发展银行")!=-1){
				bankId=bank_PF;
			}else if(resultItem.indexOf("招商银行")!=-1){
				bankId=bank_ZS;
			}else if(resultItem.indexOf("中国银行")!=-1){
				bankId=bank_ZG;
			}else if(resultItem.indexOf("邮政储蓄银行")!=-1){
				bankId=bank_YZCX;
			}else if(resultItem.indexOf("中信银行")!=-1){
				bankId=bank_ZX;
			}else if(resultItem.indexOf("兴业银行")!=-1){
				bankId=bank_XY;
			}else if(resultItem.indexOf("民生银行")!=-1){
				bankId=bank_MS;
			} else if(resultItem.indexOf("深圳发展银行")!=-1){
				bankId=bank_SZFZ;
			}else if(resultItem.indexOf("北京银行")!=-1){
				bankId=bank_BJ;
			}else if(resultItem.indexOf("广发银行")!=-1){
				bankId=bank_GF;
			}else if(resultItem.indexOf("上海银行")!=-1){
				bankId=bank_SH;
			}else if(resultItem.indexOf("平安银行")!=-1){
				bankId=bank_PA;
			}
		}
		return bankId;
	}
	
	
	/**
	 * 根据银行卡类型获取Class名-匹配银行logo
	 * @param bankType
	 * @return
	 */
	public static String getStyleClassByBankType(Integer bankType){
		String stleyClass=null;
		if(bankType==null){
			return stleyClass;
		}else if(bankType==BankType.bank_GS){
			stleyClass="gh_bank";
		}else if(bankType==BankType.bank_NY){
			stleyClass="nh_bank";
		}else if(bankType==BankType.bank_JS){
			stleyClass="jh_bank";
		}else if(bankType==BankType.bank_GD){
			stleyClass="gd_bank";
		}else if(bankType==BankType.bank_JT){
			stleyClass="jt_bank";
		}else if(bankType==BankType.bank_PF){
			stleyClass="pf_bank";
		}else if(bankType==BankType.bank_ZS){
			stleyClass="zh_bank";
		}else if(bankType==BankType.bank_ZG){
			stleyClass="zg_bank";
		}else if(bankType==BankType.bank_YZCX){
			stleyClass="yz_bank";
		}else if(bankType==BankType.bank_ZX){
			stleyClass="zx_bank";
		}else if(bankType==BankType.bank_XY){
			stleyClass="xy_bank";
		}else if(bankType==BankType.bank_MS){
			stleyClass="ms_bank";
		}else if(bankType==BankType.bank_SZFZ){
			stleyClass="szfz_bank";
		}else if(bankType==BankType.bank_BJ){
			stleyClass="bj_bank";
		}else if(bankType==BankType.bank_GF){
			stleyClass="gf_bank";
		}else if(bankType==BankType.bank_SH){
			stleyClass="sh_bank";
		}else if(bankType==BankType.bank_PA){
			stleyClass="pa_bank";
		}
		return stleyClass;
	}
	
}
