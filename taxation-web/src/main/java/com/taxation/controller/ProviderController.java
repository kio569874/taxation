package com.taxation.controller;

import com.taxation.api.common.PaginationDto;
import com.taxation.api.service.IProviderService;
import com.taxation.api.service.IUserService;
import com.taxation.bean.ProviderEntity;
import com.taxation.bean.ProviderInfoDto;
import com.taxation.bean.UserEntity;
import com.taxation.constants.CheckCodeEnum;
import com.taxation.helper.UserHelper;
import com.taxation.util.MD5Utils;
import com.taxation.web.common.LoginCodeEnum;
import com.taxation.web.util.AjaxUtils;
import com.taxation.web.util.VerifyCodeUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("provider")
public class ProviderController {
	private static Logger logger = Logger.getLogger(ProviderController.class);
	@Autowired
	private IProviderService providerService;

	/**
	 * 查询所有服务商
	 * @param model
	 * @param request
	 * @param response
     * @return
     */
	@RequestMapping(value = "/queryProvider.htm", method = RequestMethod.POST)
	public String queryProvider(Model model, @RequestBody String content,HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			int page = Integer.parseInt(map.get("pageIndex").toString());
			int pageSize = Integer.parseInt(map.get("pageSize").toString());
			PaginationDto<ProviderInfoDto> pagination = providerService.getProviderByPagination(page,pageSize);
			if (pagination != null && !CollectionUtils.isEmpty(pagination.getData())) {
				JSONObject js = JSONObject.fromObject(pagination);
				AjaxUtils.ajaxJson(response, js.toString());
			}else{

			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "查询异常");
			logger.error(e);
		}
		return null;
	}
	@RequestMapping(value = "/queryProviderInfo.htm", method = RequestMethod.POST)
	public String queryProviderInfo(Model model, @RequestBody String content,HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String providerId = map.get("providerId").toString();
			ProviderEntity providerEntity = providerService.getProviderById(providerId);
			if (providerEntity != null) {
				JSONObject js = JSONObject.fromObject(providerEntity);
				AjaxUtils.ajaxJson(response, js.toString());
			}else{

			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "查询异常");
			logger.error(e);
		}
		return null;
	}
}
