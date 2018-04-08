package com.taxation.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taxation.api.service.ISmsService;
import com.taxation.bean.SmsCheckCodeEntity;
import com.taxation.constants.CheckCodeEnum;
import com.taxation.web.util.AjaxUtils;

@Controller
@RequestMapping("sms")
public class SmsController {

	private static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private ISmsService smsService;

	@RequestMapping(value = "/sendSmsCheckCode.htm", method = RequestMethod.POST)
	public String sendSmsCheckCode(Model model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String userPhone = map.get("userPhone").toString();
			String checkCode = map.get("checkCode").toString();
			SmsCheckCodeEntity smsCheckCodeEntity = smsService.sendSmsCheckCode(userPhone,
							CheckCodeEnum.valueOf(checkCode).getCheckCodeType());
			JSONObject js = JSONObject.fromObject(smsCheckCodeEntity);
			AjaxUtils.ajaxJson(response, js.toString());
		} catch (Exception e) {
			logger.error(e);
			AjaxUtils.ajaxJsonErrorMessage(response, "发送短信验证码异常");
		}
		return null;
	}
}
