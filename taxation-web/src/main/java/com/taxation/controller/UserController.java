package com.taxation.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taxation.api.service.IUserService;
import com.taxation.bean.UserEntity;
import com.taxation.constants.CheckCodeEnum;
import com.taxation.helper.UserHelper;
import com.taxation.util.JSONBeanUtil;
import com.taxation.util.MD5Utils;
import com.taxation.web.common.LoginCodeEnum;
import com.taxation.web.util.AjaxUtils;
import com.taxation.web.util.VerifyCodeUtils;

@Controller
@RequestMapping("user")
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private IUserService userService;

	/**
	 * 用户注册
	 * 
	 * @param model
	 * @param content
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/register.htm", method = RequestMethod.POST)
	public String register(Model model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			JSONObject userJsonObj = jsonObj.getJSONObject("user");
			UserEntity userEntity = JSONBeanUtil.JsonToBean(UserEntity.class,
					userJsonObj.toString());
			// 把MD5加密后的密码放入
			userEntity.setUserPassword(MD5Utils.createMD5(userEntity
					.getUserPassword()));
			boolean flag = userService.register(userEntity);
			if (flag) {
				AjaxUtils.ajaxJsonSuccessMessage(response, "注册成功");
			} else {
				AjaxUtils.ajaxJsonErrorMessage(response, "注册失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "注册异常");
			logger.error(e);
		}
		return null;
	}

	/**
	 * 注册时检查用户名是否已存在
	 * 
	 * @param model
	 * @param content
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/checkAccount.htm", method = RequestMethod.POST)
	public String checkAccount(Model model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String userAccount = map.get("userAccount").toString();
			String checkCode = map.get("checkCode").toString();
			checkCode = CheckCodeEnum.valueOf(checkCode).getCheckCodeType();
			UserEntity userEntity = userService
					.selectByUserAccount(userAccount);
			if (userEntity != null && userEntity.getUserAccount() != null) {
				if (CheckCodeEnum.USER_REGISTER.getCheckCodeType().equals(
						checkCode)) {
					AjaxUtils.ajaxJsonErrorMessage(response, "该用户名已被注册");
				} else if (CheckCodeEnum.USER_UPDATE_PWD.getCheckCodeType()
						.equals(checkCode)
						|| CheckCodeEnum.PROVIDER_UPDATE_PWD.getCheckCodeType()
								.equals(checkCode)) {
					AjaxUtils.ajaxJsonSuccessMessage(response, "账号存在");
				}

			} else {
				if (CheckCodeEnum.USER_REGISTER.getCheckCodeType().equals(
						checkCode)) {
					// 账号可用
					AjaxUtils.ajaxJsonSuccessMessage(response, "账号可用");
				} else if (CheckCodeEnum.USER_UPDATE_PWD.getCheckCodeType()
						.equals(checkCode)
						|| CheckCodeEnum.PROVIDER_UPDATE_PWD.getCheckCodeType()
								.equals(checkCode)) {
					AjaxUtils.ajaxJsonErrorMessage(response, "账号不存在");
				}
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "验证异常");
			logger.error(e);
		}
		return null;
	}

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

	/**
	 * 用户登录
	 * 
	 * @param model
	 * @param content
	 * @param httpSession
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/userLogin.htm", method = RequestMethod.POST)
	public String userLogin(Model model, @RequestBody String content,
			HttpSession httpSession, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String verifyCode = map.get("verifyCode").toString().toUpperCase();
			String sessionVerifyCode = httpSession.getAttribute("verifyCode")
					.toString();
			if (!verifyCode.equals(sessionVerifyCode)) {
				AjaxUtils.ajaxJsonRetcodeMessage(response,
						LoginCodeEnum.LoginVerifyCode.getRetCode(), "验证码错误");
				return null;
			}
			String userAccount = map.get("userAccount").toString();
			String userPassword = map.get("userPassword").toString();

			UserEntity userEntity = userService.getUserByAccountAndPassword(
					userAccount, MD5Utils.createMD5(userPassword));
			if (userEntity == null) {
				AjaxUtils.ajaxJsonRetcodeMessage(response,
						LoginCodeEnum.LoginFailure.getRetCode(), "账号或密码输入错误");
			} else {
				// 用来存放上次登录时间
				Date tempDate = null;
				Date updateDate = new Date();
				try {
					tempDate = userEntity.getLastLoginTime();
					userEntity.setLastLoginTime(updateDate);
					userEntity.setLastLoginIp(InetAddress.getLocalHost()
							.getHostAddress().toString());
				} catch (Exception e) {
					logger.error(e);
				}
				userService.updateLoginInfoByUserAccount(userEntity);
				if (tempDate == null) {
					tempDate = updateDate;
				}
				userEntity.setLastLoginTime(tempDate);
				AjaxUtils.ajaxJsonRetcodeMessage(response,
						LoginCodeEnum.LoginSuccess.getRetCode(), userEntity,
						"登录成功");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonRetcodeMessage(response,
					LoginCodeEnum.LoginError.getRetCode(), "登录发生异常");
			logger.error(e);
		}
		return null;
	}

	// 用户退出登录
	// 检查账户对应的手机号与用户输入的手机号是否一致
	@RequestMapping(value = "/checkPhone.htm", method = RequestMethod.POST)
	public String checkPhone(Model model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String userAccount = map.get("userAccount").toString();
			String userPhone = map.get("userPhone").toString();
			UserEntity userEntity = userService
					.selectByUserAccount(userAccount);
			if (userEntity != null && userEntity.getUserAccount() != null
					&& userEntity.getUserPhone().equals(userPhone)) {
				AjaxUtils.ajaxJsonErrorMessage(response, "");
			} else {
				AjaxUtils.ajaxJsonErrorMessage(response, "此手机号与输入账号下的预留手机号不一致");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "异常");
			logger.error(e);
		}
		return null;
	}

	@RequestMapping(value = "/updatePassWord.htm", method = RequestMethod.POST)
	public String updatePassWord(Model model, @RequestBody String content,
			HttpSession httpSession, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String userAccount = map.get("userAccount").toString();
			String userPassword = map.get("userPassword").toString();
			boolean result = userService.updatePassWordByUserAccount(
					userAccount, MD5Utils.createMD5(userPassword));
			if (result) {
				// 密码修改成功后，清除用户登录session,并跳转到登录页面要求用户重新登陆
				AjaxUtils.ajaxJsonSuccessMessage(response, "密码修改成功");
			} else {
				AjaxUtils.ajaxJsonErrorMessage(response, "密码修改失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "密码修改发生异常");
			logger.error(e);
		}
		return null;
	}

	@RequestMapping(value = "/queryUserInfo.htm", method = RequestMethod.POST)
	public String queryUserInfo(Model model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String userAccount = map.get("userAccount").toString();
			UserEntity userEntity = userService
					.selectByUserAccount(userAccount);
			if (userEntity != null) {
				JSONObject js = JSONObject.fromObject(userEntity);
				AjaxUtils.ajaxJson(response, js.toString());
			} else {
				AjaxUtils.ajaxJsonErrorMessage(response, "查询用户信息失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "查询用户信息异常");
			logger.error(e);
		}
		return null;
	}

	// 用户中心/我的主页/账号安全
	@RequestMapping(value = "/securityUserInfo.htm", method = RequestMethod.POST)
	public String securityUserInfo(Model model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String userAccount = map.get("userAccount").toString();
			UserEntity userEntity = userService
					.selectByUserAccount(userAccount);
			if (userEntity != null) {
				JSONObject js = JSONObject.fromObject(userEntity);
				AjaxUtils.ajaxJson(response, js.toString());
			} else {
				AjaxUtils.ajaxJsonErrorMessage(response, "查询用户信息失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "查询用户信息异常");
			logger.error(e);
		}
		return null;
	}

	/**
	 * 绑定邮箱
	 * 
	 * @param model
	 * @param content
	 * @param httpSession
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/bindUserEmail.htm", method = RequestMethod.POST)
	public String bindUserEmail(Model model, @RequestBody String content,
			HttpSession httpSession, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String userAccount = map.get("userAccount").toString();
			String userEmail = map.get("userEmail").toString();
			boolean result = userService.bindUserEmailByUserAccount(
					userAccount, userEmail);
			if (result) {
				AjaxUtils.ajaxJsonSuccessMessage(response, "邮箱绑定成功");
			} else {
				AjaxUtils.ajaxJsonErrorMessage(response, "邮箱绑定失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "邮箱绑定发生异常");
			logger.error(e);
		}
		return null;
	}

	@RequestMapping(value = "/updateUserInfo.htm", method = RequestMethod.POST)
	public String updateUserInfo(Model model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			JSONObject userObj = (JSONObject) map.get("user");
			UserEntity userEntity = (UserEntity) JSONObject.toBean(userObj,
					UserEntity.class);// 指定转换的类型

			boolean flag = userService.updateUserEntity(userEntity);
			if (flag) {
				JSONObject js = JSONObject.fromObject(userEntity);
				AjaxUtils.ajaxJson(response, js.toString());
			} else {
				AjaxUtils.ajaxJsonErrorMessage(response, "修改用户信息失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "修改用户信息异常");
			logger.error(e);
		}
		return null;
	}
}
