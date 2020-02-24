package com.wuxin.appservice.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;


/**
 * 开奖号码生成
 * @author wuxy
 *
 */
public class RandomUtil{

  protected static final Logger LOG = Logger.getLogger(RandomUtil.class);
	
  public static String random(int size)
  {
    List<String> ranList = new ArrayList<String>();
    int r = 10000000;
    Random random = new Random();
    for (int j = 0; j < size; j++)
    {
      int a = random.nextInt(1);
      int b = random.nextInt(1);
      int c = random.nextInt(9);
      int d = random.nextInt(9);
      int e = random.nextInt(9);
      int f = random.nextInt(9);
      int g = random.nextInt(9);
      ranList.add(String.valueOf(r + (a + b + c + d + e + f + g)));
    }
    return ranList.toString().replaceAll("\\s+", "").replace("[", "").replace("]", "");
  }
  
  public static String newRandomV1(int size, int price, List<String> oldRandomList)
  {
	  long start = System.currentTimeMillis();
    List<String> randomList = new ArrayList<String>();
    int r = 10000000;
    for (int i = 1; i <= price; i++) {
      if (!oldRandomList.contains(String.valueOf(r + i))) {
        randomList.add(String.valueOf(r + i));
      }
    }
    long mmmm = System.currentTimeMillis();
    long secs = (mmmm-start)/1000;
    System.out.println(secs);
    List<String> ranList = new ArrayList<String>();
    for (int j = 0; j < size; j++) {
      if (randomList.size() > 0)
      {
        int ran = new Random().nextInt(randomList.size());
        ranList.add((String)randomList.get(ran));
        randomList.remove(ran);
      }
    }
    return ranList.toString().replaceAll("\\s+", "").replace("[", "").replace("]", "");
  }
  
  /**
   * 采用连续号段生成云购码，解决大额购买的生成效率问题
   * @param size
   * @param price
   * @param oldRandomList
   * @return
   */
	public static String newRandomV2(int size, int price, List<String> oldRandomList) {
		Map<String, Integer> randomMap = new HashMap<String, Integer>();
		int r = 10000000;
		for (int i = 1; i <= price; i++) {
			randomMap.put(String.valueOf(r + i), new Integer(0));
		}
		for (int i = 0; i < oldRandomList.size(); i++) {
			randomMap.put(oldRandomList.get(i), new Integer(1));
		}
		List<String> ranList = new ArrayList<String>();
		String[] keys = new String[randomMap.size()];
		randomMap.keySet().toArray(keys);
		Integer flag =0;
		String key = null;
		for (int i = 0, k=0; i < keys.length; i++) {
			key = keys[i];
			flag = randomMap.get(key);
			if (flag == 0) {
				ranList.add(key);
				randomMap.put(key, new Integer(1));
				k++;
			}
			if(k==size){
				break;
			}
		}
		LOG.info("大批量生成夺宝号数量"+ranList.size()+"---"+",本次购买的数量："+size);
		return ranList.toString().replaceAll("\\s+", "").replace("[", "").replace("]", "");
	}
 
  /**
   * 随机生成购买号码
   * @param size 长度
   * @param price 产品总价
   * @param oldRandomList 已经生成的购买号
   * @return
   */
  public static String newRandom(int size, int price, List<String> oldRandomList)
  {
	  //long start = System.currentTimeMillis();
	//超过500个,连续生成中奖码
	if(size>500){
	    return newRandomV2(size, price, oldRandomList);
	}
	
    Map<String,Integer> randomMap = new HashMap<String,Integer>();
    int r = 10000000;
    for (int i = 1; i <= price; i++) {//生成price个号码
    	randomMap.put(String.valueOf(r + i), new Integer(0));
    }
    for (int i = 0; i < oldRandomList.size(); i++) {//将已经生成好的号码存入，覆盖之前添加记录
    	randomMap.put(oldRandomList.get(i), new Integer(1));
    }
    //System.out.println("size="+randomMap.size());
    //long mmmm = System.currentTimeMillis();
    //long secs = (mmmm-start)/1000;
    //System.out.println(secs);
    List<String> ranList = new ArrayList<String>();
    for (int j = 0; j < size; j++) {
      if (randomMap.size() > 0)
      {
    	  int range = randomMap.size()-oldRandomList.size()-j;
    	  if(range<1){
    		  range=1;
    	  }
        int ran = new Random().nextInt(range);
        int max = Math.min(ran+oldRandomList.size()+size, randomMap.size());
        //System.out.println("ran="+ran+",max="+max);
        String[] keys = new String[randomMap.size()];
        randomMap.keySet().toArray(keys);
        for(int k=0,x=0;k<max;k++){
        	String key= keys[k];
        	Integer flag = randomMap.get(key);
        	if(flag==0){
        		if(x==ran){
        			//System.out.println("x="+x+",ran="+ran);
        			ranList.add(key);
        			randomMap.put(key, new Integer(1));
        			break;
        		}
        		x++;
        	}
        }
      }
    }
    LOG.info("生成的夺宝号码数量："+ranList.size()+"---本次要购买的次数："+size);
    return ranList.toString().replaceAll("\\s+", "").replace("[", "").replace("]", "");
  }
  
  public static void main(String[] args)
  {
    List<String> oldRandomList = new ArrayList<String>();
    int r = 10000000;
    for (int i = 1; i <= 10; i++) {
    	oldRandomList.add(String.valueOf(r + i));
    	System.out.println(r + i);
    }
    String strList = newRandom(2, 12, oldRandomList);
    System.out.println(strList);
	 /* Map<String,Integer> oldRandomList = new HashMap<String,Integer>();
	 newRandomTest(5,10,oldRandomList);*/
	  
  }
  
  public static  void newRandomTest(int size, int price,Map<String,Integer> oldRandomList){
	  Map<String,Integer> randomMap = new HashMap<String,Integer>();
	    int r = 10000000;
	    for (int i = 1; i <= price; i++) {//生成price个号码
	    	randomMap.put(String.valueOf(r + i),0);
	    }
	    StringBuffer sb=new StringBuffer();
	    Random random=new Random();
	    for (int i = 0; i <size; i++) {
	    	int number=10000000+random.nextInt(price);
	    	if(oldRandomList.get(number)==null&&sb.toString().indexOf(number)==-1){
	    		sb.append(number+",");
	    	}else{
	    		i--;
	    		continue;
	    	}
		}
	    System.out.println(sb.toString());
  }
}
