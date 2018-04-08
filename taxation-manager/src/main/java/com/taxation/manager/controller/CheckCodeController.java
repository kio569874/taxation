package com.taxation.manager.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taxation.manager.utils.AjaxUtils;
import com.taxation.manager.utils.VerifyCodeUtils;

@Controller
@RequestMapping("checkCode")
public class CheckCodeController {

	private static Logger logger = Logger.getLogger(CheckCodeController.class);

	// /**
	// * 生成验证码
	// */
	// @RequestMapping(value = "/getVerifyCode.htm")
	// public String getVerifyCode(HttpServletRequest request,
	// HttpServletResponse response,HttpSession session) throws IOException{
	// response.setContentType("image/jpeg");
	// response.setHeader("Pragma", "No-cache");
	// response.setHeader("Cache-Control", "no-cache");
	// response.setContentType("text/html;charset=UTF-8");
	// response.setCharacterEncoding("UTF-8");
	// response.setDateHeader("Expires", 0);
	// session = request.getSession();
	// // 在内存中创建图象
	// int width = 70, height = 23;
	// BufferedImage image = new BufferedImage(width,
	// height,BufferedImage.TYPE_INT_RGB);
	// // 获取图形上下文
	// Graphics g = image.getGraphics();
	// // 生成随机类
	// Random random = new Random();
	// // 设定背景色
	// g.setColor(getRandColor(200, 250));
	// g.fillRect(0, 0, width, height);
	// // 设定字体
	// g.setFont(new Font("Times New Roman", Font.PLAIN, 24));
	// // 画边框
	// g.setColor(getRandColor(160, 200));
	// g.drawRect(0, 0, width - 1, height - 1);
	// // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
	// g.setColor(getRandColor(160, 200));
	// for (int i = 0; i < 155; i++) {
	// int x = random.nextInt(width);
	// int y = random.nextInt(height);
	// int xl = random.nextInt(12);
	// int yl = random.nextInt(12);
	// g.drawLine(x, y, x + xl, y + yl);
	// }
	// // 取随机产生的认证码(4位数字)
	// String sRand = "";
	// for (int i = 0; i < 4; i++) {
	// String rand = String.valueOf(random.nextInt(10));
	// sRand += rand;
	// // 将认证码显示到图象中
	// g.setColor(new Color(20 + random.nextInt(110), 20 + random
	// .nextInt(110), 20 + random.nextInt(110)));
	// // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
	// g.drawString(rand, 13 * i + 14, 20);
	// }
	//
	// // 将认证码存入SESSION
	// session.setAttribute("checkcode", sRand);
	// // 图象生效
	// g.dispose();
	// // 输出图象到页面
	// ImageIO.write(image, "JPEG", response.getOutputStream());
	// return null;
	// }

	/**
	 * 获取验证码
	 * 
	 * @param httpSession
	 * @param response
	 */
	@RequestMapping(value = "/verifyCode.htm")
	public void getVerifyCode(HttpSession httpSession,
			HttpServletResponse response) {
		AjaxUtils.setResponseNoCache(response);
		response.setContentType("image/jpeg");

		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 存入会话session
		httpSession.setAttribute("verifyCode", verifyCode);
		// 生成图片
		int w = 100, h = 30;
		try {
			logger.info("生成的验证码为：" + verifyCode);
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(),
					verifyCode);
		} catch (IOException e) {
			logger.error("生成验证码发生异常", e);
		}
	}

	// private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
	// Random random = new Random();
	// if (fc > 255)
	// fc = 255;
	// if (bc > 255)
	// bc = 255;
	// int r = fc + random.nextInt(bc - fc);
	// int g = fc + random.nextInt(bc - fc);
	// int b = fc + random.nextInt(bc - fc);
	// return new Color(r, g, b);
	// }

}
