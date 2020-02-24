package com.wuxin.appservice.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

/**
 * 验证码工具类
 * @author wuxy
 *
 */
public class RandomValidateCode
{
  public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";
  private Random random = new Random();
  private String randString = "23456789ABCDEFGHJKLMNOPQRSTUVWXYZ";
  private int width = 100;
  private int height = 35;
  private int lineSize = 0;
  private int stringNum = 5;
  
  private Font getFont()
  {
    return new Font("Fixedsys", 1, 26);
  }
  
  private Color getRandColor(int fc, int bc)
  {
    if (fc > 255) {
      fc = 255;
    }
    if (bc > 255) {
      bc = 255;
    }
    int r = fc + random.nextInt(bc - fc - 16);
    int g = fc + random.nextInt(bc - fc - 14);
    int b = fc + random.nextInt(bc - fc - 18);
    return new Color(r, g, b);
  }
  
  public void getRandcode(HttpServletRequest request, HttpServletResponse response)
  {
    try
    {
      BufferedImage image = new BufferedImage(width, height, 4);
      Graphics g = image.getGraphics();
      g.fillRect(0, 0, width, height);
      String randomString = "";
      for (int i = 1; i <= stringNum; i++) {
        randomString = drowString(g, randomString, i);
      }
      /* 验证码不放cookie
       * if (request.isRequestedSessionIdFromCookie())
      {
        Cookie cookie = new Cookie("rndCode", randomString);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        cookie.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
        response.addCookie(cookie);
      }*/
      HttpSession session=request.getSession();
      session.setAttribute("validCode",randomString);
      session.setAttribute("createvalidCodeTime",new Date());
      g.dispose();
      ImageIO.write(image, "JPEG", response.getOutputStream());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private String drowString(Graphics g, String randomString, int i)
  {
    g.setFont(getFont());
    
    g.setColor(new Color(20, 223, 228));
    String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
    randomString = randomString + rand;
    g.translate(19, 0);
    try
    {
      g.drawString(rand, -18, 26);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return randomString;
  }
  
  private void drowLine(Graphics g)
  {
    int x = random.nextInt(width);
    int y = random.nextInt(height);
    int xl = random.nextInt(13);
    int yl = random.nextInt(15);
    g.drawLine(x, y, x + xl, y + yl);
  }
  
  public String getRandomString(int num)
  {
    return String.valueOf(randString.charAt(num));
  }
  
  /**
   * 验证码验证
   * @param request
   * @param validCode
   * @return map key=issuccess true验证成功
   */
  public Map<String,Object> checkvalidCode(HttpServletRequest request,String validCode){
	  Map<String,Object> map=new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(validCode)){
			HttpSession session=request.getSession();
			String serviceValidCode=(String) session.getAttribute("validCode");
			Date createvalidCodeDate =(Date) session.getAttribute("createvalidCodeTime");
			if(createvalidCodeDate!=null&&serviceValidCode!=null){
				int i=DateUtil.diff_date_min(new Date(),createvalidCodeDate);
				if(i<10){//验证码10分钟失效
					if(serviceValidCode.equalsIgnoreCase(validCode)){
						map.put(ConstantsParam.issuccess,true);
						return map;
					}
				}
			}
			session.removeAttribute("validCode");
			session.removeAttribute("createvalidCodeTime");
		}
		map.put(ConstantsParam.issuccess,false);
		return map;
  }
}
