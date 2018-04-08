package com.taxation.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taxation.bean.*;
import com.taxation.util.JSONBeanUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taxation.api.common.PaginationDto;
import com.taxation.api.service.IProviderService;
import com.taxation.api.service.IServiceInfoService;
import com.taxation.helper.UserAddressHelper;
import com.taxation.web.util.AjaxUtils;

@Controller
@RequestMapping("serviceInfo")
public class ServiceInfoController {
	private static Logger logger = Logger.getLogger(ProviderController.class);
	@Resource
	private IServiceInfoService serviceInfoService;
	@Resource
	private IProviderService providerService;

	/**
	 * 查询所有服务商
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	// @RequestMapping(value = "/queryService.htm", method = RequestMethod.POST)
	// public String queryProvider(Model model, @RequestBody String
	// content,HttpServletRequest request, HttpServletResponse response) {
	// try {
	// JSONObject jsonObj = JSONObject.fromObject(content);
	// Map<String, Object> map = jsonObj;
	// int page = Integer.parseInt(map.get("pageIndex").toString());
	// int pageSize = Integer.parseInt(map.get("pageSize").toString());
	// PaginationDto<ServiceDto> pagination =
	// serviceInfoService.getAllServiceByPagination(page,pageSize);
	// if (pagination != null && !CollectionUtils.isEmpty(pagination.getData()))
	// {
	// JSONObject js = JSONObject.fromObject(pagination);
	// AjaxUtils.ajaxJson(response, js.toString());
	// }else{
	//
	// }
	// } catch (Exception e) {
	// AjaxUtils.ajaxJsonErrorMessage(response, "查询异常");
	// logger.error(e);
	// }
	// return null;
	// }

	@RequestMapping(value = "/queryServiceProvider.htm", method = RequestMethod.POST)
	public String queryServiceProvider(Model model,
			@RequestBody String content, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			int page = Integer.parseInt(map.get("pageIndex").toString());
			int pageSize = Integer.parseInt(map.get("pageSize").toString());
			PaginationDto<ProviderDto> pagination = providerService
					.queryAllProviderByPage(page, pageSize);
			if (pagination != null
					&& !CollectionUtils.isEmpty(pagination.getData())) {
				JSONObject js = JSONObject.fromObject(pagination);
				AjaxUtils.ajaxJson(response, js.toString());
			} else {

			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "查询异常");
			logger.error(e);
		}
		return null;
	}

	@RequestMapping(value = "/queryServiceByProviderId.htm", method = RequestMethod.POST)
	public String queryServiceByProviderId(Model model,
			@RequestBody String content, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String providerId = map.get("providerId").toString();
			List<ServiceInfoDto> dataList = serviceInfoService
					.queryServiceByProviderId(providerId);
			if (dataList.size() > 0) {
				JSONArray jsonArray = JSONArray.fromObject(dataList);
				AjaxUtils.ajaxJson(response, jsonArray.toString());
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "查询异常");
			logger.error(e);
		}
		return null;
	}

	@RequestMapping(value = "/getServiceInfo.htm", method = RequestMethod.POST)
	public String queryService(Model model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String serviceId = map.get("serviceId").toString();
			ServiceInfoDto serviceEntity = serviceInfoService
					.getServiceById(serviceId);
			if (serviceEntity != null) {
				JSONObject js = JSONObject.fromObject(serviceEntity);
				AjaxUtils.ajaxJson(response, js.toString());
			} else {

			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "查询异常");
			logger.error(e);
		}
		return null;
	}

	@RequestMapping(value = "/queryUserAddress.htm", method = RequestMethod.POST)
	public String queryUserAddress(Model model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String userId = map.get("userId").toString();
			List<UserAddress> myUserAddress = serviceInfoService
					.getMyAddressByUserId(userId);
			if (myUserAddress.size() > 0) {
				JSONArray jsonArray = JSONArray.fromObject(myUserAddress);
				AjaxUtils.ajaxJson(response, jsonArray.toString());
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "查询异常");
			logger.error(e);
		}
		return null;
	}

	@RequestMapping(value = "/queryCityArea.htm", method = RequestMethod.POST)
	public String queryCityArea(Model model, HttpServletResponse response) {
		String result = "";
		try {
			result = serviceInfoService.getCityAreaJsonStr();
			AjaxUtils.ajaxJson(response, result);
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "查询异常");
			logger.error(e);
		}
		return null;
	}

	@RequestMapping(value = "/addNewAddress.htm", method = RequestMethod.POST)
	public String addNewAddress(Model model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean flag = serviceInfoService.addNewAddress(UserAddressHelper
					.buildUserAddressForAdd(content));
			if (flag) {
				AjaxUtils.ajaxJsonSuccessMessage(response, "添加成功");
			} else {
				AjaxUtils.ajaxJsonErrorMessage(response, "添加失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "添加异常");
			logger.error(e);
		}
		return null;
	}

	@RequestMapping(value = "/updateOldAddress.htm", method = RequestMethod.POST)
	public String updateOldAddress(Model model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean flag = serviceInfoService
					.updateOldAdressInfo(UserAddressHelper
							.buildUserAddressForUpdate(content));
			if (flag) {
				AjaxUtils.ajaxJsonSuccessMessage(response, "添加成功");
			} else {
				AjaxUtils.ajaxJsonErrorMessage(response, "添加失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "添加异常");
			logger.error(e);
		}
		return null;
	}

	@RequestMapping(value = "/queryAddressById.htm", method = RequestMethod.POST)
	public String queryAddressById(Model model, @RequestBody String content,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String addressId = map.get("addressId").toString();
			UserAddress myUserAddress = serviceInfoService
					.queryAddressById(addressId);
			if (myUserAddress != null) {
				JSONArray jsonArray = JSONArray.fromObject(myUserAddress);
				AjaxUtils.ajaxJson(response, jsonArray.toString());
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "查询异常");
			logger.error(e);
		}
		return null;
	}

	@RequestMapping(value = "/buyServiceNow.htm", method = RequestMethod.POST)
	public String buyServiceNow(Model model, @RequestBody String content,HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			JSONObject orderParamJsonObj = jsonObj.getJSONObject("orderParam");
			OrderParam orderParam = JSONBeanUtil.JsonToBean(OrderParam.class,orderParamJsonObj.toString());
			OrderEntity orderInfo = serviceInfoService.saveOrderInfo(orderParam);
			AjaxUtils.ajaxJsonRetcodeMessage(response,"true",JSONObject.fromObject(orderInfo).toString(),"订单信息保存成功");
		} catch (Exception e) {
			AjaxUtils.ajaxJsonRetcodeMessage(response,"false","订单信息保存发生异常");
			logger.error(e);
		}
		return null;
	}

	@RequestMapping(value = "/updatePayType.htm", method = RequestMethod.POST)
	public String updatePayType(Model model, @RequestBody String content,
								   HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(content);
			Map<String, Object> map = jsonObj;
			String orderId = map.get("orderId").toString();
			String payType = map.get("payType").toString();
			boolean flag = serviceInfoService.updatePayType(orderId,payType);
			if (flag) {
				AjaxUtils.ajaxJsonSuccessMessage(response, "添加成功");
			} else {
				AjaxUtils.ajaxJsonErrorMessage(response, "添加失败");
			}
		} catch (Exception e) {
			AjaxUtils.ajaxJsonErrorMessage(response, "添加异常");
			logger.error(e);
		}
		return null;
	}
}
